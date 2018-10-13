package com.demo.rpc.commom.util.properties;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 2018/10/12.
 * 字段注解
 * @author wangxiaodong
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertiesField {

    /**
     * 字段别名，默认为字段名称
     * 此字段类型只能为“只有一个String参数的构造函数的参数”
     * Integer,String,Boolean类型均可以
     * 不能为int之类的基本数据类型，否则无法注入（暂时不完善）
     * @return
     */
    String name() default "";
}
