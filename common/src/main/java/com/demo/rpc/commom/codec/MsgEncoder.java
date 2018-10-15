package com.demo.rpc.commom.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created on 2018/10/15.
 * netty消息编码
 * @author wangxiaodong
 */
public class MsgEncoder extends MessageToByteEncoder {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        try( ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream os = new ObjectOutputStream(bos)) {
            os.writeObject(msg);
            byte[] data = bos.toByteArray();
            //先写入四个字节的长度
            out.writeInt(data.length);
            out.writeBytes(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
