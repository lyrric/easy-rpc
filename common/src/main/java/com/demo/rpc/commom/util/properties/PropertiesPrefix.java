package com.demo.rpc.commom.util.properties;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 2018/10/12.
 * 类注解，指定注解浅醉
 * @author wangxiaodong
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertiesPrefix {

    /**
     * 注解前缀
     * @return
     */
    String prefix() default "";
}
