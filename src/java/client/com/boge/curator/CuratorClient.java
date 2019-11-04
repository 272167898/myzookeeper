package com.boge.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.RetryNTimes;

public class CuratorClient {
    public static void main(String[] args) throws Exception {
        CuratorFramework client =
                CuratorFrameworkFactory.newClient("47.93.203.2:2181",new RetryNTimes(3,1000));
        client.start();
        byte[] bytes = client.getData().forPath("/boge");
        System.out.println(new String(bytes));

        String path="/boge";
        NodeCache nodeCache =  new NodeCache(client,path);
        nodeCache.start();
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("66666啊");
            }
        });
        System.in.read();
    }
}
