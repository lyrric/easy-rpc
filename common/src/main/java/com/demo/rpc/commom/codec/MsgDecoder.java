package com.demo.rpc.commom.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created on 2018/10/15.
 * netty消息解码
 * @author wangxiaodong
 */
public class MsgDecoder extends ByteToMessageDecoder {

    /**
     * 消息头int类型，长度为4
     */
    private byte HEAD_LENGTH = 4;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //判断消息头是否传送完毕
        if(in.readableBytes() < HEAD_LENGTH){
            return;
        }
        //保存开始位置,如果长度不够的话，需要回到起始位置
        in.markReaderIndex();
        //获取消息预计发送长度
        int length = in.readInt();
        if(in.readableBytes() < length){
            //消息提未发送完
            in.resetReaderIndex();
            return;
        }
        /**
         * 反序列化
         */
        byte[] data = new byte[length];
        in.readBytes(data);
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object object = ois.readObject();
        out.add(object);
    }
}
