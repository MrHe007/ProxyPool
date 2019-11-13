package com.bigguy.spider.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtils {

    /**
     * 创建缓存线程池
     * @return
     */
    public static ExecutorService newCachedThreadPool(){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        return cachedThreadPool;
    }

    /**
     * 传建固定线程
     * @param threadNum
     * @return
     */
    public static ExecutorService newFixedThreadPool(int threadNum){
        ExecutorService cachedThreadPool = Executors.newFixedThreadPool(threadNum);
        return cachedThreadPool;
    }

}
