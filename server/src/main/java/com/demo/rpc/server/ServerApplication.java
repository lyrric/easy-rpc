package com.demo.rpc.server;

import com.demo.rpc.commom.util.properties.PropertiesUtil;
import com.demo.rpc.server.model.ServerProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Created on 2018/10/12.
 *
 * @author wangxiaodong
 */
public class ServerApplication {

    private static final String CONFIG_FILE_NAME = "rpc-server.properties";

    public static void main(String[] args) throws IOException {
        ServerProperties serverProperties = getProperties();

    }

    /**
     * 获取配置信息
     * @return
     * @throws IOException
     */
    private static ServerProperties getProperties() throws IOException {
        Properties properties = new Properties();
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_FILE_NAME);
        properties.load(in);
        in.close();
        return PropertiesUtil.convert(properties, ServerProperties.class);
    }

    /**
     * 启动netty
     * @param properties
     */
    private static void startNetty(ServerProperties properties){

    }
}
