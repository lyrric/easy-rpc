package com.demo.rpc.commom;

import lombok.Data;

/**
 * Created on 2018/10/15.
 *
 * @author wangxiaodong
 */
@Data
public class RpcRequest {
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
    private Class<?> parameterTypes;
    /**
     * 参数
     */
    private Object[] parameters;
    /**
     * 请求时间
     */
    private Long requestTime;
}
