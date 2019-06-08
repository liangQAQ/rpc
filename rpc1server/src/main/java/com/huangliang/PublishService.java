package com.huangliang;

import com.huangliang.service.HelloServiceImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 将服务发布出去
 */
public class PublishService {

    ExecutorService executorService = Executors.newCachedThreadPool();

    public void publish() throws Exception {
        ServerSocket serverSocket= new ServerSocket(8000);
        while (true){
            Socket client = serverSocket.accept();
            executorService.execute(new ProxyHandler(client,new HelloServiceImpl()));
        }
    }
}
