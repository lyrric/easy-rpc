package com.demo.rpc.server;

import com.demo.rpc.commom.util.properties.PropertiesUtil;
import com.demo.rpc.server.handler.MessageHandler;
import com.demo.rpc.server.model.ServerProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.apachecommons.CommonsLog;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Properties;


/**
 * Created on 2018/10/12.
 *
 * @author wangxiaodong
 */
@CommonsLog
public class ServerApplication {



    private static final String CONFIG_FILE_NAME = "rpc-server.properties";

    public static void run(String[] args) {
        try {
            log.info(LocalDateTime.now().toString() + "||" + "Read properties from "+CONFIG_FILE_NAME);
            ServerProperties serverProperties = getProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    private static void startNetty(ServerProperties properties) throws InterruptedException {
        log.info(LocalDateTime.now().toString() + "||" +"Start netty on port:"+properties.getPort() + "||login required:" + properties.getLoginFlag());
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel)  {
                        socketChannel.pipeline().addLast(new MessageHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        ChannelFuture future = bootstrap.bind(properties.getPort()).sync();
        log.info(LocalDateTime.now().toString() + "||" +"Start netty success||now waiting request");
        future.channel().closeFuture().sync();

    }
}
