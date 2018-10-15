package com.demo.rpc.server.util;

import com.demo.rpc.server.annotation.RpcService;
import lombok.Data;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.HashMap;
import java.util.Set;

/**
 * Created on 2018/10/15.
 * 类工具
 * @author wangxiaodong
 */
public class ClassUtil {


    /**
     * service接口实现数据
     */
    private static HashMap<String, ServiceItem> serviceMap;

    /**
     * 加入类接口实现数据
     * @param className 接口类名称
     * @param obj 实现类的实例
     * @param clazz 实现类
     */
    private static void add(String className, Object obj, Class<?> clazz){
        if(serviceMap == null)
            serviceMap=new HashMap<>();
        serviceMap.put(className, new ServiceItem(obj, clazz));
    }

    /**
     * 扫描带@RpcService注解的类并保存到map
     */
    public static void scanService(String basePackage){
        // 不使用默认的TypeFilter
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(RpcService.class));
        Set<BeanDefinition> beanDefinitionSet = provider.findCandidateComponents(basePackage);
        for(BeanDefinition beanDefinition:beanDefinitionSet){
            try {
                Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass(beanDefinition.getBeanClassName());
                RpcService rpcService = clazz.getAnnotation(RpcService.class);
                add(rpcService.value().getSimpleName(), null, clazz);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    @Data
    private static class ServiceItem {
        /**
         * 类的实例
         */
        private Object object;
        /**
         * 类
         */
        private Class clazz;

        ServiceItem(Object object, Class clazz) {
            this.object = object;
            this.clazz = clazz;
        }
    }
}
