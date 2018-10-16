package com.demo.rpc.client;

import com.demo.rpc.client.handler.MessageHandler;
import com.demo.rpc.client.model.ClientProperties;
import com.demo.rpc.commom.codec.MsgDecoder;
import com.demo.rpc.commom.codec.MsgEncoder;
import com.demo.rpc.commom.model.RpcRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.apachecommons.CommonsLog;

import java.util.logging.Handler;

/**
 * Created on 2018/10/16.
 * netty管理
 * @author wangxiaodong
 */
@CommonsLog
public class NettyService {

    private static SocketChannel socketChannel = null;
    /**
     * 启动连接
     * @param properties
     * @throws InterruptedException
     */
    public static void startNetty(ClientProperties properties)  {
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(worker)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel)  {
                            socketChannel.pipeline().addLast(new MsgEncoder());
                            socketChannel.pipeline().addLast(new MsgDecoder());
                            socketChannel.pipeline().addLast(new MessageHandler());
                        }
                    })
                    .option(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = bootstrap.connect(properties.getHost(), properties.getPort()).sync();
            socketChannel = (SocketChannel) future.channel();
            log.info(properties.getHost()+":"+properties.getPort()+"，连接已建立");
            //异步等待关闭，避免阻塞线程
/*            new Thread(()->{
                try {
                    future.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });*/
        } catch (InterruptedException e) {
            e.printStackTrace();
            worker.shutdownGracefully();
        }


    }

    /**
     * 发送消息
     * @param request
     */
    public static void send(RpcRequest request){
        socketChannel.writeAndFlush(request);
    }
}
