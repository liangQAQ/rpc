package com.huangliang.rpcclientzk;

import java.lang.reflect.Proxy;

public class RpcProxyClient {

    /**
     * 通过接口类型，主机名，端口，来获取被代理后的对象
     * @param interfaces
     * @param <T>
     * @return
     */
    public <T>T getProxy(Class<T> interfaces){
        RpcProxy proxy = new RpcProxy(interfaces);
        return (T) Proxy.newProxyInstance(interfaces.getClassLoader(),new Class[]{interfaces},proxy);
    }
}
