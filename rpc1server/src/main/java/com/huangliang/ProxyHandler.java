package com.huangliang;

import com.huangliang.api.entity.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ProxyHandler implements Runnable {

    private Socket client;

    public ProxyHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
            RpcRequest request = (RpcRequest) objectInputStream.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
