package com.demo.rpc.client.proxy;

import com.demo.rpc.client.NettyService;
import com.demo.rpc.client.SyncFutureMgr;
import com.demo.rpc.client.model.SyncResFuture;
import com.demo.rpc.commom.model.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created on 2018/10/16.
 * 动态代理，发送请求
 * @author wangxiaodong
 */
public class RpcProxyHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class == method.getDeclaringClass()) {
            String methodName = method.getName();
            if ("equals".equals(methodName)) {
                return proxy == args[0];
            } else if ("hashCode".equals(methodName)) {
                return System.identityHashCode(proxy);
            } else if ("toString".equals(methodName)) {
                return proxy.getClass().getName() + "@" +
                        Integer.toHexString(System.identityHashCode(proxy)) +
                        ", with InvocationHandler " + this;
            } else {
                throw new IllegalStateException(String.valueOf(method));
            }
        }
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setRequestId(UUID.randomUUID().toString());
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        rpcRequest.setParameterTypes(method.getParameterTypes());
        rpcRequest.setRequestTime(System.currentTimeMillis());
        NettyService.send(rpcRequest);
        //保存请求
        SyncResFuture future = new SyncResFuture();
        SyncFutureMgr.put(rpcRequest.getRequestId(), future);
        //返回数据
        return future.getResponse().getData();
    }
}
