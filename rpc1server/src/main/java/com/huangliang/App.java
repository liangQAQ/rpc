package com.huangliang;

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
