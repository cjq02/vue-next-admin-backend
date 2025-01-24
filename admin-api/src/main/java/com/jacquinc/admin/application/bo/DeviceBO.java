package com.jacquinc.admin.application.bo;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.jiujie.framework.base.utils.JSONUtils;
import com.jiujie.framework.base.utils.StringUtils;
import com.jacquinc.admin.application.constants.Constants;
import com.jacquinc.admin.application.exception.ValidateException;
import com.jacquinc.admin.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;

public class DeviceBO implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(DeviceBO.class);

    private String device;      //ios android
    private String deviceuuid;  //设备uuid
    private String version;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDeviceuuid() {
        return deviceuuid;
    }

    public void setDeviceuuid(String deviceuuid) {
        this.deviceuuid = deviceuuid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void validatorBean(DeviceBO deviceVO) throws ValidateException {
        if (deviceVO == null) {
            throw new ValidateException(Constants.ErrorCode.PARAMS_NULL_ERROR, true);
        }
        if (StringUtils.isBlank(deviceVO.getDevice())) {
            throw new ValidateException(Constants.ErrorCode.PARAMS_NULL_ERROR, true);
        }
        if (StringUtils.isBlank(deviceVO.getDeviceuuid())) {
            throw new ValidateException(Constants.ErrorCode.PARAMS_NULL_ERROR, true);
        }
        if (StringUtils.isBlank(deviceVO.getVersion())) {
            throw new ValidateException(Constants.ErrorCode.PARAMS_NULL_ERROR, true);
        }
    }

    public static void validatorSign(String profiles, Map<String, Object> params, String sign, long time) {
        if (Constants.LOCAL.equals(profiles) && StringUtils.isEmpty(sign)) {
            log.info("本地开发测试不硬性要求签名！");
            return;
        }
        if (StringUtils.isEmpty(sign)) {
            throw new ValidateException(Constants.ErrorCode.PARAMS_NULL_ERROR, true);
        } else {
            // timesign校验，防止抓包后直接提交
            // timesign: 2018-05-04 11:18:30 - checkTimesign:2018-05-04 11:18:20 current: 2018-05-04 11:50:39
            Date date = new Date(time);
            Date checkTimesign = DateUtils.addMinutes(date, 5);
            Date current = DateUtils.getCurrentDate();
            if (checkTimesign.before(current)) {
                throw new ValidateException(Constants.ErrorCode.PARAMS_SIGN_ERROR, true);
            }
            params.put("time", time);
            log.info("排序前签名验证参数：{}", JSONUtils.mapToString(params));
            Map<String, Object> sortParams = jsonToMap(JSONObject.toJSONString(params));
            String md5 = SecureUtil.md5(JSONUtils.mapToString(sortParams)).toUpperCase();
            String _sign = new HMac(HmacAlgorithm.HmacSHA1, Constants.SIGN_KEY.getBytes()).digestHex(md5).toUpperCase();
            log.info("排序后签名验证参数：{}，app签名：{}，服务端签名：{}", JSONUtils.mapToString(sortParams), sign, _sign);
            if (!sign.equals(_sign)) {
                throw new ValidateException(Constants.ErrorCode.PARAMS_SIGN_ERROR, true);
            }
        }
    }

    /**
     * JSON转顺序排序的Map(为了方便后期获取数据,应答就不返回JSON字符串了,可自行去转换)
     *
     * @param jsonStr 原始json
     * @return 响应的map
     */
    private static Map<String, Object> jsonToMap(String jsonStr) {
        Map<String, Object> treeMap = new TreeMap<>();
        JSONObject json = JSON.parseObject(jsonStr, Feature.OrderedField);//Feature.OrderedField实现解析后保存不乱序
        for (String key : json.keySet()) {
            Object value = json.get(key);
            //判断传入kay-value中是否含有""或null
            if (value == null || StringUtils.isEmpty(value.toString())) {
                //当JSON字符串存在null时,不将该kay-value放入Map中,即显示的结果不包括该kay-value
                treeMap.put(key, "");
                continue;
            }
            if (StringUtils.isEmpty(StringUtils.trim(value.toString()))) {
                treeMap.put(key, value);
                continue;
            }
            // 判断是否为JSONArray(json数组)
            if (value instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) value;
                List<Object> arrayList = new ArrayList<>();
                for (Object object : jsonArray) {
                    // 判断是否为JSONObject，如果是 转化成TreeMap
                    if (object instanceof JSONObject) {
                        object = jsonToMap(object.toString());
                    }
                    arrayList.add(object);
                }
                treeMap.put(key, arrayList);
            } else {
                //判断该JSON中是否嵌套JSON
                boolean flag = isJSONValid(value.toString());
                if (flag) {
                    //若嵌套json了,通过递归再对嵌套的json(即子json)进行排序
                    value = jsonToMap(value.toString());
                    treeMap.put(key, value);
                } else {
                    // 其他基础类型直接放入treeMap
                    // JSONObject可进行再次解析转换
                    treeMap.put(key, value.toString());
                }
            }
        }
        return treeMap;
    }

    /**
     * 校验是否是JSON字符串
     *
     * @param json 传入数据
     * @return 是JSON返回true, 否则false
     */
    private static boolean isJSONValid(String json) {
        try {
            JSONObject.parseObject(json);
        } catch (JSONException ex) {
            return false;
        }
        return true;
    }


    public static Map<String, Object> generator(String globalDevice, String globalDeviceuuid, String globalVersion, String json) {
        Map<String, Object> returnMap = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        long time = System.currentTimeMillis();
        params.put("Global-Device", globalDevice);
        params.put("Global-Device-UUID", globalDeviceuuid);
        params.put("Global-Version", globalVersion);
        params.put("time", time);
        JSONObject jsonObject = JSONObject.parseObject(json);
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            params.put(entry.getKey(), entry.getValue());
        }

        Map<String, Object> sortParams = new LinkedHashMap<String, Object>();
        params.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(map ->
                sortParams.put(map.getKey(), map.getValue().toString()));
        String md5 = SecureUtil.md5(JSONUtils.mapToString(sortParams)).toUpperCase();
        String sign = new HMac(HmacAlgorithm.HmacSHA1, Constants.SIGN_KEY.getBytes()).digestHex(md5).toUpperCase();

        returnMap.put("sign", sign);
        returnMap.put("time", time);
        return returnMap;
    }
}
