/*
Copyright (C) 2011-$today.year. ShenZhen IBOXCHAIN Information Technology Co.,Ltd.

All right reserved.

This software is the confidential and proprietary
information of IBOXCHAIN Company of China.
("Confidential Information"). You shall not disclose
such Confidential Information and shall use it only
in accordance with the terms of the contract agreement
you entered into with IBOXCHAIN inc.

*/
package com.bigguy.spider.service;

import com.bigguy.spider.entity.ProxyEntity;
import com.bigguy.spider.thread.ProxyValidataTask;
import com.bigguy.spider.util.ThreadPoolUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author ：hechen
 * @data ：2019/11/13
 * @description ：校验代理有效性
 */
@Service
public class CheckValidateService {

    @Autowired
    RedisService redisService;

    ExecutorService executorService = ThreadPoolUtils.newFixedThreadPool(20);

    /**
     * 检验一个ip
     * @param proxyEntity
     */
    public void check(ProxyEntity proxyEntity){
        executorService.submit(new ProxyValidataTask(proxyEntity, redisService));
    }

    /**
     * 校验代理列表
     * @param proxyEntityList
     */
    public void check(List<ProxyEntity> proxyEntityList){
        for (ProxyEntity proxyEntity : proxyEntityList) {
            executorService.submit(new ProxyValidataTask(proxyEntity, redisService));
        }
    }
}
