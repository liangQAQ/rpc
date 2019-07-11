package com.huangliang.rpcserverzk.config;

import com.huangliang.rpcserverzk.service.HelloServiceImpl;
import com.huangliang.rpcserverzk.service.StudentServiceImpl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class IocContainer {

    public static Class[] arr = new Class[]{HelloServiceImpl.class,StudentServiceImpl.class};

    public static Map<String,Class> ioc = new HashMap<>();

    static {
        initIoc();
    }

    private static void initIoc() {
        //模拟扫描注解
        for(Class clazz : arr){
            if(clazz.getInterfaces().length<=0){
                continue;
            }
            for(Class interFace :clazz.getInterfaces()){
                ioc.put(interFace.getSimpleName(),clazz);
            }
        }
    }
}
