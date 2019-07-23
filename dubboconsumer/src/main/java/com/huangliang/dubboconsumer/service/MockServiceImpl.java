package com.huangliang.dubboconsumer.service;

import com.huangliang.api.service.HelloService;

/**
 * 降级处理
 */
public class MockServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "服务端响应超时，返回本地数据";
    }
}
