package com.huangliang.dubboprovider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.huangliang.api.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello1"+name;
    }
}
