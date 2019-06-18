package com.huangliang.netty.controller;


import com.huangliang.netty.annotation.HLController;
import com.huangliang.netty.annotation.HLRequestMapping;

@HLController("/pre")
public class TestController {

    @HLRequestMapping("/test")
    public String test(String name){
        return "hello11"+name;
    }
}
