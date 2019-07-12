package com.huangliang.rpcserverzk.service;

import com.huangliang.api.service.HelloService;
import com.huangliang.rpcserverzk.annotation.zkNode;

@zkNode
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        System.out.println("hello :" + name);
        return "hello:"+name;
    }
}
