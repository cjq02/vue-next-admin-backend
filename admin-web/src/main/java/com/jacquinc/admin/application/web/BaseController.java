package com.jacquinc.admin.application.web;

import com.jacquinc.admin.application.constants.Constants;
import com.jacquinc.admin.sys.enumerate.RoleTypeEnum;
import com.jacquinc.admin.sys.service.ICodeService;
import com.jacquinc.admin.sys.vo.UserVOExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 基类控制器
 *
 * @author zmxie
 */
@SuppressWarnings("WeakerAccess")
@Component
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.profiles}")
    protected String env;

    protected static final ThreadLocal<HttpServletRequest> requestThread = new NamedThreadLocal<>("Request");

    protected static final ThreadLocal<HttpServletResponse> responseThread = new NamedThreadLocal<>("Response");

    @Autowired
    private ICodeService codeService;

    /**
     * 将请求和响应对象放在本地线程中
     *
     * @param request  请求
     * @param response 响应
     */
    @ModelAttribute
    public void setModelServlet(HttpServletRequest request, HttpServletResponse response) {
        requestThread.set(request);
        responseThread.set(response);
    }

    protected HttpServletRequest getRequest() {
        return requestThread.get();
    }

    /**
     * 获取当前用户对象
     *
     * @return 用户
     */
    protected UserVOExt getCurrentUser() {
        return (UserVOExt) getRequest().getSession().getAttribute(Constants.SESSION_USER);
    }

    /**
     * 获取当前用户ID
     *
     * @return 用户ID
     */
    protected String getCurrentUserId() {
        return getCurrentUser().getId();
    }

    protected String getCurrentCorpId() {
        return getCurrentUser().getCorpId();
    }

    protected String getCurrentDeptId() {
        return getCurrentUser().getDepartmentId();
    }

    protected boolean isSuperAdmin() {
        return getCurrentUser().getRoleTypes().contains(RoleTypeEnum._01.getCode());
    }

    /**
     * 去异常页面 需要补充错误控制器
     *
     * @param model 模板
     * @return 路径
     */
    protected String errorJsp(Model model, Exception e) {
        model.addAttribute("exception", e);
        return "/error";
    }

    /**
     * 设置session的attribute
     *
     * @param session 会话
     * @param name    名称
     * @param code    编码
     */
    protected void setSessionAttribute(HttpSession session, String name, String code) {
        session.setAttribute(name, code);
    }

    /**
     * 获取session的attribute
     *
     * @param session 会话
     * @param name    名称
     * @return 结果
     */
    protected String getSessionAttribute(HttpSession session, String name) {
        return (String) session.getAttribute(name);
    }

    @SuppressWarnings("SpellCheckingInspection")
    protected String getSidx() {
        return getRequest().getParameter("sidx");
    }

    @SuppressWarnings("SpellCheckingInspection")
    protected String getSord() {
        return getRequest().getParameter("Sord");
    }

    protected static final int PAGE_SIZE = Constants.PAGE_SIZE;

    protected int offsetHandler(Integer page) {
        if (page == null || page == 0) {
            page = 1;
        }
        return (page - 1) * PAGE_SIZE;
    }
}
