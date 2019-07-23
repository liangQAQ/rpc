package com.huangliang.dubboprovider.service;

import com.huangliang.api.service.HelloService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        System.out.println("hello");
        return "hello1"+name;
    }
}
