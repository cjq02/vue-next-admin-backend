package com.jacquinc.admin.common.util;

import org.springframework.util.Assert;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public final class CookieUtils {

    private static final String REQUEST_MESSAGE = "not has request";
    private static final String NAME_MESSAGE = "not has name";

    public static String getCookie(HttpServletRequest request, String name) {
        Assert.notNull(request, REQUEST_MESSAGE);
        Assert.hasText(name, NAME_MESSAGE);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            try {
                name = URLEncoder.encode(name, "UTF-8");
                for (Cookie cookie : cookies) {
                    if (name.equals(cookie.getName())) {
                        return URLDecoder.decode(cookie.getValue(), "UTF-8");
                    }
                }
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}