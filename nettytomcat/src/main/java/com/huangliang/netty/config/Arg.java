package com.huangliang.netty.config;

/**
 * 方法的参数描述
 */
public class Arg {

    private String argName;
    private Class clazz;

    public Arg(String argName, Class clazz) {
        this.argName = argName;
        this.clazz = clazz;
    }

    public String getArgName() {
        return argName;
    }

    public void setArgName(String argName) {
        this.argName = argName;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
