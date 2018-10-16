package com.demo.rpc.simple.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created on 2018/10/15.
 *
 * @author wangxiaodong
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String username;

}
