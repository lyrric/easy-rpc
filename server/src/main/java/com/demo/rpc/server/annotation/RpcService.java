package com.demo.rpc.server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 2018/10/13.
 * 接口注解，用于暴露接口
 * @author wangxiaodong
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RpcService {

    /**
     * 这个在实现类上，表示要实现的接口
     * @return
     */
    Class<?> value();
}
