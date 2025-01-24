package com.jacquinc.admin.application.exception;

import com.jiujie.framework.adapter.vo.ResponseResult;
import com.jiujie.framework.base.utils.JSONUtils;
import com.jiujie.framework.base.utils.StringUtils;
import com.jiujie.framework.exception.BusinessException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.StoppedSessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * BmsExceptionHandler
 *
 * @author zhouyw
 * created on  2017.08.22
 */
@ControllerAdvice
public class BmsExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * biz
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public String bizErrorHandler(HttpServletResponse response, BusinessException e) throws IOException {
        logger.error("biz error-->e={}", e.getMessage(), e);
        return errorHandler(response, e, StringUtils.isEmpty(e.getCode()) ? HttpServletResponse.SC_OK : Integer.parseInt(e.getCode()));
    }

    /**
     * 权限未受权
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    public String unauthorizedErrorHandler(HttpServletResponse response, UnauthorizedException e) throws IOException {
        logger.error("当前登录用户没有该权限", e);
        return errorHandler(response, e, HttpServletResponse.SC_FORBIDDEN);
    }

    /**
     * 用户Session会话停止
     *
     * @param e
     */
    @ExceptionHandler(value = StoppedSessionException.class)
    public String stoppedSessionErrorHandler(HttpServletResponse response, StoppedSessionException e) throws IOException {
        logger.error("当前登录用户Session会话停止", e);
        return errorHandler(response, e, HttpServletResponse.SC_UNAUTHORIZED);
    }

    /**
     * runtimeException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    public String runtimeExceptionHandle(HttpServletResponse response, RuntimeException e) throws IOException {
        logger.error("runtimeException", e);
        return errorHandler(response, new RuntimeException("服务异常，请联系管理员！"), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    private String errorHandler(HttpServletResponse response, RuntimeException e, int status) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(status);
        PrintWriter out = response.getWriter();
        out.println(JSONUtils.objectToString(new ResponseResult(false, null, e.getMessage())));
        out.flush();
        return "";
    }
}