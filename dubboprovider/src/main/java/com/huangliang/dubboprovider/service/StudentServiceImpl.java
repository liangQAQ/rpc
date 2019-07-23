package com.huangliang.dubboprovider.service;

import com.huangliang.api.entity.Student;
import com.huangliang.api.service.StudentService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public void save(Student student) {
        System.out.println(student);
    }
}
