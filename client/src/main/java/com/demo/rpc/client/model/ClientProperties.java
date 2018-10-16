package com.demo.rpc.client.model;

import lombok.Data;

/**
 * Created on 2018/10/16.
 * 客户端配置
 * @author wangxiaodong
 */
@Data
public class ClientProperties {
    /**
     * server地址
     */
    private String host = "127.0.0.1";
    /**
     * server端口
     */
    private Integer port = 9898;
}
