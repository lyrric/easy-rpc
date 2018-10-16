package com.demo.rpc.commom.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created on 2018/10/15.
 * rpc请求
 * @author wangxiaodong
 */
@Data
public class RpcRequest implements Serializable {
    /**
     * requestId
     */
    private String requestId;
    /**
     * 预调用类名
     */
    private String className;
    /**
     * 预调用方法
     */
    private String methodName;
    /**
     * 参数类型
     */
    private Class<?>[] parameterTypes;
    /**
     * 参数
     */
    private Object[] parameters;
    /**
     * 请求时间
     */
    private Long requestTime;
}
