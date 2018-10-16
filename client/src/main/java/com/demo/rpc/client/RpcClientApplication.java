package com.demo.rpc.client;

import com.demo.rpc.client.model.ClientProperties;
import com.demo.rpc.commom.util.properties.PropertiesUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created on 2018/10/16.
 * 客户端启动类
 * @author wangxiaodong
 */
public class RpcClientApplication {

    private static String CONFIG_FILE = "rpc-client.properties";

    public static void run(String[] args) {
        try {
            //读取配置文件
            ClientProperties clientProperties = gerProperties();
            NettyService.startNetty(clientProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //启动netty
    }
    private static ClientProperties gerProperties() throws IOException {
        Properties properties = new Properties();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_FILE);
        properties.load(is);
        return PropertiesUtil.convert(properties, ClientProperties.class);
    }

}
