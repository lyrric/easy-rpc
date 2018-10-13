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
@PropertiesPrefix(prefix = "server")
public class ServerProperties {

    @PropertiesField(name = "port")
    private Integer port;
}
