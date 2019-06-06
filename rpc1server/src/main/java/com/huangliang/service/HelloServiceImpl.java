package com.huangliang.service;

import com.huangliang.api.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello:"+name;
    }
}
