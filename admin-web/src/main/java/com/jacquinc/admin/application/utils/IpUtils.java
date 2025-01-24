package com.jacquinc.admin.application.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class IpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(IpUtils.class);

    // 获取ip，多级代理取第一个ip
    public static String getRemoteIp4(HttpServletRequest request) {
        String ip = getRemoteIpV4(request);
        if (ip == null) {
            ip = "0.0.0.0";
        }
        String[] ips = ip.split(",");
        return ips[0];
    }

    // 获取ip地址，多级代理返回一串ip
    public static String getRemoteIpV4(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");


        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    // 测试代理ip，端口是否可用
    public static boolean checkAddressPort(String address, Integer port) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(address, port));
        } catch (IOException e) {
//            LOGGER.error("代理ip不可用，ip：{}，端口：{}", address, port);
            return false;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}