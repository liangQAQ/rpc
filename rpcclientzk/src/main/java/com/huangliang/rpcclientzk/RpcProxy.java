package com.huangliang.rpcclientzk;

import com.huangliang.api.config.ZKConfig;
import com.huangliang.api.entity.RpcRequest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class RpcProxy implements InvocationHandler {

    private RpcRequest rpcRequest;

    private String host;
    private Integer port;
    private Class interfaces;

    public RpcProxy(RpcRequest rpcRequest) {
        rpcRequest = rpcRequest;
    }

    public RpcProxy() {
    }

    public <T> RpcProxy(Class<T> interfaces, String host, int port) {
        this.host = host;
        this.port = port;
        this.interfaces = interfaces;
    }
    public <T> RpcProxy(Class<T> interfaces) {
        this.interfaces = interfaces;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String zkPath = ZKConfig.rootPath +"/" +interfaces.getName()+"/"+method.getName();
        String addresses = new ZkConfig(zkPath).getAddressByPath();
        host = addresses.split(":")[0];
        port = Integer.valueOf(addresses.split(":")[1]);
        Socket s = new Socket(host,port);
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutStream = null;
        Object result = null;
        try{
            objectOutStream = new ObjectOutputStream(s.getOutputStream());
            rpcRequest = new RpcRequest();
            rpcRequest.setArgs(args);
            rpcRequest.setClassName(interfaces.getName());
            rpcRequest.setMethodName(method.getName());
            rpcRequest.setSimpleClassName(interfaces.getSimpleName());
            objectOutStream.writeObject(rpcRequest);
            objectInputStream = new ObjectInputStream(s.getInputStream());
            result = objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            objectInputStream.close();
            objectOutStream.close();
        }
        return result;
    }
}
