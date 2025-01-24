package com.jacquinc.admin.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.jacquinc.admin.sys.service.ILogService;
import com.jacquinc.admin.sys.vo.LogVO;
import com.jacquinc.admin.sys.vo.UserVOExt;
import com.jacquinc.admin.utils.DateUtils;
import com.jacquinc.admin.utils.StringUtils;
import com.jiujie.framework.base.utils.JSONUtils;
import com.jiujie.framework.exception.BusinessException;
import com.jiujie.framework.spring.context.utils.SpringContextUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengzheng on 2017/9/1.
 */
public class SysLogUtils {

    private static ILogService logService = SpringContextUtil.getBean(ILogService.class);

    public static Map<String, Object> getRequestParams(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<>();
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            request.getParameterMap().forEach((key, value) -> params.put(key, request.getParameter(key)));
        }
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            if (request.getContentType() != null && request.getContentType().contains("multipart/form-data")) {
                request.getParameterMap().forEach((key, value) -> {
                    if (!key.equals("file") && !key.equals("files")) {
                        params.put(key, request.getParameter(key));
                    }
                });
            } else {
                Map<String, String[]> parameterMap = request.getParameterMap();
                if (parameterMap != null && !parameterMap.isEmpty()) {
                    parameterMap.forEach((key, value) -> params.put(key, request.getParameter(key)));
                } else {
                    StringBuilder buffer = new StringBuilder();
                    try {
                        //获取请求头对象,页面信息存放在请求头里面
                        BufferedReader reader = request.getReader(); String line = null;
                        //循环调用readLine方法将reader中的每条不为null,类型为“String”数据放入json中
                        while (null != (line = reader.readLine())) {
                            buffer.append(line);
                        }
                        if (StringUtils.isNotEmpty(buffer.toString())) {
                            JSONObject jsonObject = JSON.parseObject(buffer.toString());
                            params.putAll(jsonObject);
                        }
                    } catch (JSONException e) {
                        params.put("data", "json解析异常：" + buffer);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new BusinessException("解析错误，请联系客服！");
                    }
                }
            }
        }
        return params;
    }

    // 异步线程保存日志
    public static void saveLog(HttpServletRequest request, Object args, Throwable e, String remarks, String userId) {
        String parameter = null;
        if (!request.getRequestURI().contains("htmlToExcel") && null != args) {
            parameter = JSONUtils.objectToString(args);
        }
        LogVO logVO = new LogVO();
        logVO.setUrl(request.getRequestURI());
        logVO.setParameter(parameter);
        logVO.setIp(StringUtils.getRemoteAddr(request));
        logVO.setCreateTs(DateUtils.getCurrentDate());
        logVO.setStatus("0");
        logVO.setRemark(remarks);
        logVO.setCreateUserId(userId);
        // 异步保存日志
        new SaveLogThread(logVO, e).start();
    }

    /**
     * 保存日志线程
     */
    public static class SaveLogThread extends Thread {

        private LogVO logVO;
        private Throwable e;

        private SaveLogThread(LogVO logVO, Throwable e) {
            super(SaveLogThread.class.getSimpleName());
            this.logVO = logVO;
            this.e = e;
        }

        @Override
        public void run() {
            // 如果有异常，设置异常信息
            logVO.setException(Exceptions.getStackTraceAsString(e));
            if (!StringUtils.isBlank(logVO.getException())) {
                logVO.setStatus("1");
            }
            // 保存日志信息
            logService.save(logVO);
        }
    }
}