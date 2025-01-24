package com.jacquinc.admin.application.mybatis;

import com.jacquinc.admin.application.vo.Page;
import com.jacquinc.admin.utils.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class SortInterceptor implements Interceptor {

    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();

    private boolean isQueryList(String statementId) {
        String id = statementId.toLowerCase();
        if (id.contains("count")) {
            return false;
        }
        return id.endsWith("list") || id.endsWith("page");
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");

        if (!isQueryList(mappedStatement.getId())) {
            return invocation.proceed();
        }

        Map<String, Object> params = (Map<String, Object>) metaStatementHandler.getValue("delegate.boundSql.parameterObject");
        if (params == null || !params.containsKey("page")) {
            return invocation.proceed();
        }
        Page page = (Page) params.get("page");
        if (StringUtils.isNotEmpty(page.getSortBy())) {
            String oldSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
            String newSql = null;
            System.out.println("****************  当前排序字段：" + page.getSortBy() + "，排序方式：" + page.getSortType() + "  ****************");

            int orderByIdx = oldSql.toLowerCase().indexOf("order by");
            int limitIdx = oldSql.toLowerCase().indexOf("limit");
            if (orderByIdx > 0) {
                newSql = oldSql.substring(0, orderByIdx);
            }
            if (limitIdx > 0) {
                String limitStr = oldSql.substring(limitIdx);
                newSql = String.format("select * from (%s) t order by %s %s NULLS LAST %s", newSql, page.getSortBy(), page.getSortType(), limitStr);
            } else {
                newSql = String.format("select * from (%s) t order by %s %s NULLS LAST", newSql, page.getSortBy(), page.getSortType());
            }
            metaStatementHandler.setValue("delegate.boundSql.sql", newSql);
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
