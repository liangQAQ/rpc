package com.huangliang;

import com.huangliang.api.entity.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProxyHandler implements Runnable {

    private Socket client;
    private Object service;


    public ProxyHandler(Socket client, Object service) {
        this.client = client;
        this.service = service;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutStream = null;
        try {
            objectInputStream = new ObjectInputStream(client.getInputStream());
            RpcRequest request = (RpcRequest) objectInputStream.readObject();
            Object result = invoke(request);
            System.out.println("result = "+result);
            objectOutStream = new ObjectOutputStream(client.getOutputStream());
            objectOutStream.writeObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                objectInputStream.close();
                objectOutStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Object invoke(RpcRequest request){
        Object[] args = request.getArgs();//获取请求参数
        Class[] types = new Class[args.length];
        Class clazz = null;
        Method method = null;
        Object result = null;
        for(int i=0;i<types.length;i++){
            types[i] = args[i].getClass();
        }
        try {
            clazz = Class.forName(service.getClass().getName());
            method = clazz.getMethod(request.getMethodName(),types);
            result = method.invoke(service,args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
