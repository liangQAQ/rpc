package com.huangliang.client;

import com.huangliang.api.service.HelloService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        HelloService helloService = (HelloService)new RpcProxyClient().getProxy(HelloService.class,"127.0.0.1",8000);
        System.out.println(helloService.hello("xixi"));
    }
}
