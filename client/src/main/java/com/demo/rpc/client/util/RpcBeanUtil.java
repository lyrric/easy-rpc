package com.demo.rpc.client.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2018/10/16.
 *
 * @author wangxiaodong
 */
public class RpcBeanUtil {
    /**
     * 保存实例化的bean
     */
    private static Map<String, Object> beanMap = new HashMap<>();

    /**
     * 获取bean
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        return (T)beanMap.get(clazz.getName());
    }

    /**
     * 加入bean
     * @param clazz
     * @param object
     */
    public static void put(Class clazz, Object object){
        String name = clazz.getName();
        beanMap.put(name, object);
    }
}
