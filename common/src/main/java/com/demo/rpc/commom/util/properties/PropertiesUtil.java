package com.demo.rpc.commom.util.properties;

import com.demo.rpc.commom.exception.MissPropertiesException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Created on 2018/10/12.
 *
 * @author wangxiaodong
 */
public class PropertiesUtil {

    /**
     * 将properties转换为指定类的对象
     * @param properties
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T convert(Properties properties, Class<T> clazz){
        try {
            T t = clazz.newInstance();
            String prefix = getClassPrefix(clazz);
            Field []fields = clazz.getDeclaredFields();
            for(Field field:fields){
                field.setAccessible(true);
                String key = "".equals(prefix)?"":prefix+".";
                key+= getFiledName(field);
                String strValue = properties.getProperty(key);
                if(strValue != null && !"".equals(strValue)){
                    Class type = field.getType();
                    //尝试将配置文件值类型转换为对应属性类型
                    //此方法字段类型只能为“只有一个String参数的构造函数的参数”
                    //Integer,String,Boolean类型均可以
                    // 不能为int之类的基本数据类型，否则无法注入（暂时不完善）
                    Object obj = type.getConstructor(String.class).newInstance(strValue);
                    field.set(t, obj);
                }else if(isRequired(field)){
                    //如果参数必须设置，则抛出异常
                    throw new MissPropertiesException("未配置:"+field.getName()+"");
                }
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取类PropertiesPrefix注解prefix值
     * @param clazz
     * @return
     */
    private static String getClassPrefix(Class clazz){
        if(clazz.isAnnotationPresent(PropertiesPrefix.class)){
            PropertiesPrefix propertiesPrefix = (PropertiesPrefix) clazz.getAnnotation(PropertiesPrefix.class);
            return propertiesPrefix.prefix();
        }
        return "";
    }

    /**
     * 获取类PropertiesField注解name值
     * @param field
     * @return
     */
    private static String getFiledName(Field field){
        if(field.isAnnotationPresent(PropertiesField.class)){
            PropertiesField propertiesField = field.getAnnotation(PropertiesField.class);
            return propertiesField.name();
        }
        return field.getName();
    }

    /**
     * 判断是否必须为字段赋值
     * @param field
     * @return
     */
    private static boolean isRequired(Field field){
        if(field.isAnnotationPresent(PropertiesField.class)){
            PropertiesField propertiesField = field.getAnnotation(PropertiesField.class);
            return propertiesField.required();
        }
        return false;
    }
}
