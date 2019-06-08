package com.huangliang.client;

import java.lang.reflect.Proxy;

public class RpcProxyClient {

    /**
     * 通过接口类型，主机名，端口，来获取被代理后的对象
     * @param interfaces
     * @param host
     * @param port
     * @param <T>
     * @return
     */
    public <T>T getProxy(Class<T> interfaces,String host,int port){
        RpcProxy proxy = new RpcProxy(interfaces,host,port);
        return (T) Proxy.newProxyInstance(interfaces.getClassLoader(),new Class[]{interfaces},proxy);
    }
}
