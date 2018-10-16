package com.demo.rpc.server.handler;

import com.demo.rpc.commom.model.RpcRequest;
import com.demo.rpc.commom.model.RpcResponse;
import com.demo.rpc.server.util.ClassUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;

import java.lang.reflect.InvocationTargetException;

/**
 * Created on 2018/10/13.
 *
 * @author wangxiaodong
 */
@ChannelHandler.Sharable
@CommonsLog
public class MessageHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info(ctx.channel().remoteAddress()+", 连接");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        RpcRequest rpcRequest = (RpcRequest)msg;
        ctx.channel().writeAndFlush(handle(rpcRequest));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        log.info("一个连接关闭了");
    }

    /**
     * 异步调用并返回数据
     * @param rpcRequest
     */
    private RpcResponse handle(RpcRequest rpcRequest){
        String clazzName = rpcRequest.getClassName();
        String methodName =rpcRequest.getMethodName();
        //获取对应类的实例
        Object serviceBean = ClassUtil.get(clazzName);
        //先暂时什么都不做，有时间再返回错误信息
        if(serviceBean == null) {
            return null;
        }
        //反射，执行方法
        FastClass fastClass = FastClass.create(serviceBean.getClass());
        FastMethod fastMethod = fastClass.getMethod(methodName, rpcRequest.getParameterTypes());

        try {
            Object object = fastMethod.invoke(serviceBean, rpcRequest.getParameters());
            //封装返回数据
            RpcResponse rpcResponse = new RpcResponse();
            rpcResponse.setData(object);
            rpcResponse.setRequestId(rpcRequest.getRequestId());
            rpcResponse.setSuccess(true);
            return rpcResponse;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
