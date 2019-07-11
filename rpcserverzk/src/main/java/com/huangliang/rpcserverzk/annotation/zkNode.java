package com.huangliang.rpcserverzk.annotation;

import java.lang.annotation.*;

/**
 * 带有该注解的类，将接口所拥有的方法注册进zk
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface zkNode {
}
