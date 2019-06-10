package com.huangliang.client;

import com.huangliang.api.entity.Student;
import com.huangliang.api.service.HelloService;
import com.huangliang.api.service.StudentService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){

        HelloService helloService = new RpcProxyClient().getProxy(HelloService.class,"127.0.0.1",8000);
        StudentService studentService = new RpcProxyClient().getProxy(StudentService.class,"127.0.0.1",8000);
        System.out.println(helloService.hello("xixi"));
        studentService.save(new Student("sss",12));

    }
}
