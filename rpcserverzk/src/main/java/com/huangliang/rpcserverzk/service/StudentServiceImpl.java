package com.huangliang.rpcserverzk.service;

import com.huangliang.api.entity.Student;
import com.huangliang.api.service.StudentService;

public class StudentServiceImpl implements StudentService {
    @Override
    public void save(Student student) {
        System.out.println("保存:"+student);
    }
}
