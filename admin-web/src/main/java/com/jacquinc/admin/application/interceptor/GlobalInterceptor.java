package com.jacquinc.admin.application.interceptor;

import com.jacquinc.admin.common.util.SysLogUtils;
import com.jacquinc.admin.application.bo.DeviceBO;
import com.jacquinc.admin.application.constants.Constants;
import com.jacquinc.admin.sys.vo.UserVOExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Map;

@Component
public class GlobalInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(GlobalInterceptor.class);

    private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("ThreadLocal StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if (RequestMethod.OPTIONS.name().equals(request.getMethod())) {
            return true;
        }
        Map<String, Object> params = SysLogUtils.getRequestParams(request);
        // 添加公共参数
        DeviceBO deviceBO = new DeviceBO();
        deviceBO.setDevice(request.getHeader("Global-Device"));
        deviceBO.setDeviceuuid(request.getHeader("Global-Device-UUID"));
        deviceBO.setVersion(request.getHeader("Global-Version"));
        deviceBO.validatorBean(deviceBO);
        params.put("Global-Device", deviceBO.getDevice());
        params.put("Global-Device-UUID", deviceBO.getDeviceuuid());
        params.put("Global-Version", deviceBO.getVersion());
        request.getSession().setAttribute(Constants.SESSION_DEVICE, deviceBO);
        request.getSession().setAttribute(Constants.REQUEST_PARAMS, params);
        String requestURI = request.getRequestURI();
        // 打印JVM信息。
        if (log.isDebugEnabled()) {
            long beginTime = System.currentTimeMillis();//1、开始时间
            startTimeThreadLocal.set(beginTime);		//线程绑定变量（该数据只有当前请求的线程可见）
            log.debug("开始计时: {}  URI: {}", new SimpleDateFormat("hh:mm:ss.SSS")
                    .format(beginTime), requestURI);
        }
        UserVOExt user = (UserVOExt) request.getSession().getAttribute(Constants.SESSION_USER);
        SysLogUtils.saveLog(request, params, null, "sale-web", user == null ? null : user.getId());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        if (RequestMethod.OPTIONS.name().equals(request.getMethod())) {
            return;
        }
        if (log.isDebugEnabled()) {
            long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
            long endTime = System.currentTimeMillis(); 	//2、结束时间
            log.debug("计时结束: {}  耗时: {}  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                    new SimpleDateFormat("hh:mm:ss.SSS").format(endTime), (endTime - beginTime) + "ms",
                    request.getRequestURI(), Runtime.getRuntime().maxMemory()/1024/1024,
                    Runtime.getRuntime().totalMemory()/1024/1024, Runtime.getRuntime().freeMemory()/1024/1024,
                    (Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024);
        }
        if (null != e) {
            UserVOExt user = (UserVOExt) request.getSession().getAttribute(Constants.SESSION_USER);
            SysLogUtils.saveLog(request, null, e, "sale-web", user == null ? null : user.getId());
        }
    }
}
