package com.huangliang.rpcclientzk;

import com.huangliang.api.config.ZKConfig;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;
import java.util.Random;

public class ZkConfig {

    private static final String zkAddress = ZKConfig.zkAddress;

    private static CuratorFramework curatorFramework;

    private String path;

    static {
        //初始化zk客户端
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(zkAddress).sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        curatorFramework.start();
    }

    public ZkConfig(String path) {
        this.path = path;
    }

    public String getAddressByPath(){
        String result = null;
        try {
            result = getBalance(curatorFramework.getChildren().forPath(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 负载均衡从服务里随机选择一个地址用于调用
     * @param strings
     * @return
     */
    private String getBalance(List<String> strings) {
        if(strings==null||strings.size()==0){
            return null;
        }
        return strings.get(new Random().nextInt(strings.size()));
    }

    public static void main(String[] args) {
        System.out.println(new Random().nextInt(3));//0,1,2
    }
}
