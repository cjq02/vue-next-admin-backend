package com.jacquinc.admin.utils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by hongjianhui on 2018/12/27.
 */
public class GetChangeDataUtil {
    /**
     * <p>Title: compareFields</p>
     * <p>Description: </p>
     * 比较两个实体属性值
     *
     * @param original  原始的对象
     * @param target    修改后的对象
     * @param includeArr 忽略的字段
     * @return
     */
    public static Map<String, Object> compareFields(Object original, Object target, String[] includeArr) {
        if (original == null || target == null || includeArr == null || includeArr.length == 0) {
            return null;
        }
        try {
            Map<String, Object> map = new HashMap<>();
            // array转化为list
            List<String> includeList = Arrays.asList(includeArr);
            if (original.getClass() == target.getClass()) {// 只有两个对象都是同一类型的才有可比性
                Class clazz = original.getClass();
                // 获取object的属性描述
                PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz,
                        Object.class).getPropertyDescriptors();
                for (PropertyDescriptor pd : pds) {// 这里就是所有的属性了
                    String name = pd.getName();// 属性名
                    if (!includeList.contains(name)) {// 如果当前属性选择忽略比较，跳到下一次循环
                        continue;
                    }
                    Method readMethod = pd.getReadMethod();// get方法
                    // 在obj1上调用get方法等同于获得obj1的属性值
                    Object o1 = readMethod.invoke(original);
                    // 在obj2上调用get方法等同于获得obj2的属性值
                    Object o2 = readMethod.invoke(target);
                    if (o1 instanceof Date) {
                        o1 = DateUtils.dateToString((Date) o1);
                    }
                    if (o2 instanceof Date) {
                        o2 = DateUtils.dateToString((Date) o2);
                    }
                    if (o1 instanceof BigDecimal) {
                        o1 = ((BigDecimal) o1).floatValue();
                    }
                    if (o2 instanceof BigDecimal) {
                        o2 = ((BigDecimal) o2).floatValue();
                    }
                    if (o1 == null && o2 == null) {
                        continue;
                    } else if (o1 == null) {
                        map.put(name + "_old", "");
                        map.put(name, o2);
                        continue;
                    }
                    if (!o1.equals(o2)) {// 比较这两个值是否相等,不等就可以放入map了
                        map.put(name + "_old", o1);
                        map.put(name, o2);
                    }
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}