package com.jacquinc.admin.application.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 回话状态处理
 *
 * @author zmxie
 */
@WebFilter(urlPatterns = {"*.htm", "*.json"})
public class SessionStatusFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	//empty
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        if (user == null) {
            //用户会话超时,设置为401
            ((HttpServletResponse) response).setStatus(401);
        }*/

        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
    	//empty
    }
}
