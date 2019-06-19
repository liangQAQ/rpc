package com.huangliang.netty.config;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodHelper {

    /**
     * javassist字节码技术获取方法形参
     * Arg<argName,argClass>
     * 不用map保存是因为遍历的时候set集合的排序问题(不是先进先出)
     * @param clazz
     */
    public static List<Arg> getMethodArgNames(Class clazz, Method method){
//        Map<String,Class> result = new HashMap<>();
        List<Arg> args = new ArrayList<>();
        try {
            //获取要操作的类对象
            ClassPool pool = ClassPool.getDefault();
            CtClass ctClass = pool.get(clazz.getName());

            //获取要操作的方法参数类型数组，为获取该方法代表的CtMethod做准备
            int count = method.getParameterCount();
            Class<?>[] paramTypes = method.getParameterTypes();
            CtClass[] ctParams = new CtClass[count];
            for (int i = 0; i < count; i++) {
                ctParams[i] = pool.getCtClass(paramTypes[i].getName());
            }

            CtMethod ctMethod = ctClass.getDeclaredMethod(method.getName(), ctParams);
            //得到该方法信息类
            MethodInfo methodInfo = ctMethod.getMethodInfo();

            //获取属性变量相关
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();

            //获取方法本地变量信息，包括方法声明和方法体内的变量
            //需注意，若方法为非静态方法，则第一个变量名为this
            LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
            int pos = Modifier.isStatic(method.getModifiers()) ? 0 : 1;

            for (int i = 0; i < count; i++) {
                args.add(new Arg(attr.variableName(i + pos),method.getParameterTypes()[i]));
//                result.put(attr.variableName(i + pos),method.getParameterTypes()[i]);
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return args;
    }
}
