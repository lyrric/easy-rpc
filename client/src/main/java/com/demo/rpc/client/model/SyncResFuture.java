package com.demo.rpc.client.model;

import com.demo.rpc.commom.model.RpcResponse;

import java.util.concurrent.CountDownLatch;

/**
 * Created on 2018/10/16.
 *
 * @author wangxiaodong
 */
public class SyncResFuture {
    /**
     * 阻塞等待获取响应
     */
    private CountDownLatch latch = new CountDownLatch(1);
    /**
     * 响应数据
     */
    private RpcResponse response;


    /**
     * 获取响应数据，知道有数据才返回
     * @return
     * @throws InterruptedException
     */
    public RpcResponse getResponse() throws InterruptedException {
        latch.await();
        return response;
    }

    public void setResponse(RpcResponse response) {
        this.response = response;
        latch.countDown();
    }
}
