package com.demo.rpc.server.model;

import com.demo.rpc.commom.util.properties.PropertiesPrefix;
import com.demo.rpc.commom.util.properties.PropertiesField;
import lombok.Data;

/**
 * Created on 2018/10/12.
 *
 * @author wangxiaodong
 */
@Data
@PropertiesPrefix(prefix = "rpc.server")
public class ServerProperties {

    /**
     * rpc端口
     */
    @PropertiesField(name = "port")
    private Integer port = 8899;

    /**
     * 是否检验token
     */
    @PropertiesField(name = "login")
    private Boolean loginFlag = false;

    /**
     * token
     */
    @PropertiesField(name = "token")
    private String token = "";

    @PropertiesField(name = "service.package", required = true)
    private String servicePackage;
}
