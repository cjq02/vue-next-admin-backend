package com.jacquinc.admin.application.web.imports;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.jacquinc.admin.application.annotation.ImportHandler;
import com.jacquinc.admin.application.constants.Constants;
import com.jacquinc.admin.sys.enumerate.RoleTypeEnum;
import com.jacquinc.admin.sys.service.IRoleService;
import com.jacquinc.admin.sys.service.IUserService;
import com.jacquinc.admin.sys.vo.UserVOExt;
import com.jiujie.framework.exception.BusinessException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author cjq
 * created on  2020/09/01
 */
@Component
@ImportHandler(name = "User")
public class UserImportHandler extends AbstractImportHandler {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @Override
    public void execute(HttpServletRequest request, Sheet sheet, String currentUserId) {
        String corpId = request.getParameter("corpId");
        List<UserVOExt> userList = new ArrayList<>();
        List<String> roleIds = getRoleIds();
        int length = sheet.getLastRowNum();
        Instant start = Instant.now();
        //0行是标题 从第一行开始
        for (int i = 1; i <= length; i++) {
            try {
                Row row = sheet.getRow(i);

                UserVOExt vo = new UserVOExt();
                vo.setActive(Constants.YES);
                vo.setUserName(getCell(row, ColMap.USER_NAME));
                vo.setJobNo(getCell(row, ColMap.JOB_NO));
                vo.setRealName(getCell(row, ColMap.REAL_NAME));
                vo.setPhone(getCell(row, ColMap.PHONE));

                userService.validate(vo);

                vo.setCorpId(corpId);
                vo.setRoleIds(roleIds);

                Instant end = Instant.now();
                Duration duration = Duration.between(start, end);
                userList.add(vo);
                logger.info("已经检查通过第{}条数据，剩余{}条, 耗时：{}秒", i, length - i, duration.getSeconds());
            } catch (BusinessException e) {
                throw new BusinessException(String.format("导入失败！第%d行: %s", i + 1, e.getMessage()));
            }
        }
        userService.save(userList, currentUserId);
    }

    private List<String> getRoleIds() {
        String roleId = Optional.ofNullable(roleService.getRoleByType(RoleTypeEnum._02.getCode()))
                .orElseThrow(() -> new BusinessException("不存在角色类型为职工的角色ID")).getId();
        return Lists.newArrayList(roleId);
    }

    private static class ColMap {
        private final static List<Object> USER_NAME = ImmutableList.of(0, "用户名", true);
        private final static List<Object> JOB_NO = ImmutableList.of(1, "工号", true);
        private final static List<Object> REAL_NAME = ImmutableList.of(2, "姓名", true);
        private final static List<Object> PHONE = ImmutableList.of(3, "手机号");
    }
}
