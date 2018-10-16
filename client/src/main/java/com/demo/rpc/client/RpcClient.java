package com.demo.rpc.client;

import com.demo.rpc.client.proxy.RpcProxyHandler;
import com.demo.rpc.client.util.RpcBeanUtil;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2018/10/16.
 * 工具bean
 * @author wangxiaodong
 */
public class RpcClient {



    /**
     * 获取代理后的bean
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public synchronized static <T> T getBean(Class<T> clazz){
        T t = RpcBeanUtil.getBean(clazz);
        if(t == null){
            t = (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz},new RpcProxyHandler());
            RpcBeanUtil.put(clazz,  t);
        }
        return t;
    }


}
