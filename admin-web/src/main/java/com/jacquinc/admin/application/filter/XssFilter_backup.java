//package com.jacquinc.admin.application.filter;
//
//import org.apache.commons.lang3.BooleanUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * 拦截防止xss注入
// * 通过Jsoup过滤请求参数内的特定字符
// * @author jhhong
// */
//public class XssFilter_backup implements Filter {
//    private static Logger logger = LoggerFactory.getLogger(XssFilter.class);
//
//    private static boolean IS_INCLUDE_RICH_TEXT = false;//是否过滤富文本内容
//
//    public List<String> excludes = new ArrayList<>();
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,ServletException {
//        if(logger.isDebugEnabled()){
//            logger.debug("xss filter is open");
//        }
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
//        if(handleExcludeURL(req, resp)){
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
//        filterChain.doFilter(xssRequest, response);
//    }
//
//    private boolean handleExcludeURL(HttpServletRequest request, HttpServletResponse response) {
//
//        if (excludes == null || excludes.isEmpty()) {
//            return false;
//        }
//
//        String url = request.getServletPath();
//        for (String pattern : excludes) {
//            Pattern p = Pattern.compile("^" + pattern);
//            Matcher m = p.matcher(url);
//            if (m.find()) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        if(logger.isDebugEnabled()){
//            logger.debug("xss filter init~~~~~~~~~~~~");
//        }
//        String isIncludeRichText = filterConfig.getInitParameter("isIncludeRichText");
//        if(StringUtils.isNotBlank(isIncludeRichText)){
//            IS_INCLUDE_RICH_TEXT = BooleanUtils.toBoolean(isIncludeRichText);
//        }
//
//        String temp = filterConfig.getInitParameter("excludes");
//        if (temp != null) {
//            String[] url = temp.split(",");
//            for (int i = 0; url != null && i < url.length; i++) {
//                excludes.add(url[i]);
//            }
//        }
//    }
//
//    @Override
//    public void destroy() {}
//}