package com.huangliang.rpcserverzk;

import com.huangliang.rpcserverzk.config.PublishService;
import com.huangliang.rpcserverzk.config.RegisterZK;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            System.out.println("启动rpc服务端...");
            new PublishService().publish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
