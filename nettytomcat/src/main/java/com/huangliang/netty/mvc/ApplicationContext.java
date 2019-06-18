package com.huangliang.netty.mvc;

import com.huangliang.netty.annotation.HLController;
import com.huangliang.netty.annotation.HLRequestMapping;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    //保存所有相关的服务类
    private static List<String> classNames = new ArrayList<String>();

    public static Map<String,HandlerMapping> mapping = new ConcurrentHashMap<>();

    public ApplicationContext() {
        init();
    }

    public void init(){
        //扫描带特定注解的类
        scan("com.huangliang.netty");
        //将其注册进mapping中
        register();
    }

    private void register() {
        for(String className : classNames){
            try {
                Class clazz = Class.forName(className);
                Method[] methods = clazz.getMethods();
                if(!clazz.isAnnotationPresent(HLController.class)){
                    continue;
                }
                HLController controller = (HLController) clazz.getAnnotation(HLController.class);
                if(methods.length<=0){
                    continue;
                }
                String pre = controller.value();
                for(Method m : methods){
                    if(!m.isAnnotationPresent(HLRequestMapping.class)){
                        continue;
                    }
                    String url = pre +  m.getAnnotation(HLRequestMapping.class).value();
                        mapping.put(url,new HandlerMapping(className,m,clazz.newInstance()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void scan(String packageName) {
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            //如果是一个文件夹，继续递归
            if(file.isDirectory()){
                scan(packageName + "." + file.getName());
            }else{
                classNames.add(packageName + "." + file.getName().replace(".class", "").trim());
            }
        }

    }
}
