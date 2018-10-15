package com.demo.rpc.commom.exception;

import java.util.concurrent.Executors;

/**
 * Created on 2018/10/15.
 * 配置缺少异常
 * @author wangxiaodong
 */
public class MissPropertiesException extends Exception {
    public MissPropertiesException(String message) {
        super(message);
    }
}
