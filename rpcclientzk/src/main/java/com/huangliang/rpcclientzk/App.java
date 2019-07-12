package com.huangliang.rpcclientzk;

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

        HelloService helloService = new RpcProxyClient().getProxy(HelloService.class);
        StudentService studentService = new RpcProxyClient().getProxy(StudentService.class);
        System.out.println(helloService.hello("xixi"));
        studentService.save(new Student("sss",12));

    }
}
