package com.huangliang.rpcserverzk.config;

import com.huangliang.api.config.ZKConfig;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 将服务注册进zookeeper
 */
public class RegisterZK {

    private String zkAddress = ZKConfig.zkAddress;

    private String host = "127.0.0.1:"+PublishService.port;

    private String rootPath = ZKConfig.rootPath;

    public void register() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(zkAddress).sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        curatorFramework.start();
        for(Map.Entry<String, Class> entry : IocContainer.ioc.entrySet()){
            Class interFace = entry.getValue().getInterfaces()[0];
            for(Method method : interFace.getDeclaredMethods()){
                String zkPath =rootPath+ "/"+interFace.getName()+"/"+method.getName()+"/"+host;
                curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(zkPath);
            }
        }
    }
}
