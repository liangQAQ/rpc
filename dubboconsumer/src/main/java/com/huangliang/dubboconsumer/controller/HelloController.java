package com.huangliang.dubboconsumer.controller;

import com.huangliang.api.entity.Student;
import com.huangliang.api.service.HelloService;
import com.huangliang.api.service.StudentService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Reference(loadbalance="roundrobin",timeout = 1,mock="com.huangliang.dubboconsumer.service.MockServiceImpl")
    private HelloService helloService;

    @Reference
    private StudentService studentService;

    @RequestMapping("/hello.do")
    public String hello(String name){
        return helloService.hello(name);
    }

    @RequestMapping("/save.do")
    public String student(){
        studentService.save(new Student("xixi",22));
        return "success";
    }
}
