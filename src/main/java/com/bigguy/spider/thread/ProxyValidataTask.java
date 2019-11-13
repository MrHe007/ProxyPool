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
package com.bigguy.spider.thread;

import com.bigguy.spider.cst.ParamCst;
import com.bigguy.spider.entity.ProxyEntity;
import com.bigguy.spider.service.RedisService;
import com.bigguy.spider.util.JSONUtils;
import com.bigguy.spider.util.ProxyUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author ：hechen
 * @data ：2019/11/13
 * @description ：校验一个任务
 */
@Slf4j
public class ProxyValidataTask implements Runnable{

    ProxyEntity proxyEntity;
    RedisService redisService;

    public ProxyValidataTask(ProxyEntity proxyEntity, RedisService redisService) {
        this.proxyEntity = proxyEntity;
        this.redisService = redisService;
    }

    @Override
    public void run() {

        ProxyUtils.ProxyType proxyType = ProxyUtils.ProxyType.HTTP;

        if(StringUtils.isNotBlank(proxyEntity.getProxyType()) && "HTTPS".toUpperCase().equals(proxyEntity.getProxyType().toUpperCase())){
            proxyType = ProxyUtils.ProxyType.HTTPS;
        }

        if(ProxyUtils.validateIp(proxyEntity.getIp(), proxyEntity.getPort(), proxyType)){
            // 入缓存、设置默认的 score
            proxyEntity.setScore(proxyEntity.getScore()==null ? 100 : null);
            log.info("validate proxy：{}", JSONUtils.toJSON(proxyEntity));
            // 默认 socre 100 分
            proxyEntity.setScore(100);
            redisService.set(ParamCst.RedisCst.ROOT_PATH + proxyEntity.getIp(), JSONUtils.toJSON(proxyEntity));
        }else {

            // 说明是从数据库中取出的 proxy
            if(null != proxyEntity.getScore()){
                // 减分
                log.info("proxy score -1：{}", JSONUtils.toJSON(proxyEntity));
                proxyEntity.setScore(proxyEntity.getScore()-1);
                redisService.set(ParamCst.RedisCst.ROOT_PATH + proxyEntity.getIp(), JSONUtils.toJSON(proxyEntity));
            }else{
                log.info("fail proxy：{}", JSONUtils.toJSON(proxyEntity));
            }
        }
    }
}