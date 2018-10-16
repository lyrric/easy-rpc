package com.demo.rpc.simple.client;

import com.demo.rpc.client.RpcClient;
import com.demo.rpc.client.RpcClientApplication;
import com.demo.rpc.client.proxy.RpcProxyHandler;
import com.demo.rpc.simple.api.model.User;
import com.demo.rpc.simple.api.service.TestService;

import java.lang.reflect.Proxy;

/**
 * Created on 2018/10/16.
 *
 * @author wangxiaodong
 */
public class SimpleClientApp {

    public static void main(String[] args) {
        RpcClientApplication.run(args);
        TestService testService = RpcClient.getBean(TestService.class);
        User user = testService.findById(1);
        System.out.println(user);
    }
}
