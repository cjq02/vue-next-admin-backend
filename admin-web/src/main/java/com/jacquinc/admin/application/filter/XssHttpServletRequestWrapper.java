package com.jacquinc.admin.application.filter;

import com.jiujie.framework.exception.BusinessException;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        return StringEscapeUtils.escapeHtml4(super.getHeader(name));
    }

    @Override
    public String getQueryString() {
        return StringEscapeUtils.escapeHtml4(super.getQueryString());
    }

    @Override
    public String getParameter(String name) {
        String initValue = super.getParameter(name);
        if (name =="userName" && initValue !="" && initValue !=null && initValue.length()>20) {
            throw new BusinessException("用户名或者密码不能超过20个字符!");
        }
        String value = StringEscapeUtils.escapeHtml4(initValue);
        if (null != value) {
            value = escape(value);
        }
        return value;
    }

//    @Override
//    public String[] getParameterValues(String name) {
//        String[] values = super.getParameterValues(name);
//        if (values != null) {
//            int length = values.length;
//            String[] escapseValues = new String[length];
//            for (int i = 0; i < length; i++) {
//                escapseValues[i] = StringEscapeUtils.escapeHtml4(values[i]);
//            }
//            return escapseValues;
//        }
//        return super.getParameterValues(name);
//    }

    public static String escape(String s) {
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '>':
                    sb.append("");// 全角大于号
                    break;
                case '<':
                    sb.append("");// 全角小于号
                    break;
                case '＞':
                    sb.append("");// 全角大于号
                    break;
                case '＜':
                    sb.append("");// 全角小于号
                    break;
                case '\'':
                    sb.append("");// 全角单引号
                    break;
                case '\"':
                    sb.append("");// 全角双引号
                    break;
                case '\\':
                    sb.append("");// 全角斜线
                    break;
                case ';':
                    sb.append("");//
                    break;
                case '&':
                    sb.append("");//
                    break;
                case '$':
                    sb.append("");//
                    break;
                case '@':
                    sb.append("");//
                    break;
                case '|':
                    sb.append("");//
                    break;
                case '(':
                    sb.append("");//
                    break;
                case ')':
                    sb.append("");//
                    break;
                case '+':
                    sb.append("");//
                    break;
                case ',':
                    sb.append("");
                    break;
                case '%':
                    sb.append(""); // 全角冒号
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

}
