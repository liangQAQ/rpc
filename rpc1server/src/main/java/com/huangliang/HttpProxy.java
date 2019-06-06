package com.huangliang;

import com.huangliang.api.entity.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HttpProxy implements InvocationHandler {

    private RpcRequest RpcRequest;

    public Object getInstance(RpcRequest target) throws Exception {
        this.RpcRequest = target;
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    public HttpProxy(RpcRequest rpcRequest) {
        RpcRequest = rpcRequest;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
