package com.huangliang.rpcserverzk.config;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 将服务发布出去
 */
public class PublishService {

    public static Integer port = 8002;

    ExecutorService executorService = Executors.newCachedThreadPool();

    public void publish() throws Exception {
        new RegisterZK().register();
        ServerSocket serverSocket= new ServerSocket(port);
        while (true){
            Socket client = serverSocket.accept();
            executorService.execute(new ProxyHandler(client));
        }
    }
}
