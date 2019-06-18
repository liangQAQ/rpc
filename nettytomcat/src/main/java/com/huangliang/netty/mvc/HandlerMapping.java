package com.huangliang.netty.mvc;

import java.lang.reflect.Method;

public class HandlerMapping {
    private String className;
    private Method m;
    private Object object;

    public HandlerMapping(String className, Method m, Object object) {
        this.className = className;
        this.m = m;
        this.object = object;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Method getM() {
        return m;
    }

    public void setM(Method m) {
        this.m = m;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
