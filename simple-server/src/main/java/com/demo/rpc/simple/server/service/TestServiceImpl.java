package com.demo.rpc.simple.server.service;

import com.demo.rpc.server.annotation.RpcService;
import com.demo.rpc.simple.api.model.User;
import com.demo.rpc.simple.api.service.TestService;

/**
 * Created on 2018/10/15.
 *
 * @author wangxiaodong
 */
@RpcService(TestService.class)
public class TestServiceImpl implements TestService {

    @Override
    public User findById(Integer id) {
        User user = new User();
        user.setId(id);
        user.setUsername("admin");
        return user;
    }
}
