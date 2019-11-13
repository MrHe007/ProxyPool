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
package com.bigguy.spider.spider.xici;

import com.bigguy.spider.entity.ProxyEntity;
import com.bigguy.spider.service.RedisService;
import com.bigguy.spider.util.ProxyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * @author ：hechen
 * @data ：2019/11/12
 * @description ：
 */
public class XiciPipeline implements Pipeline {

    @Autowired
    RedisService redisService;

    @Override
    public void process(ResultItems resultItems, Task task) {

        String url = resultItems.getRequest().getUrl();
        System.out.println(url);
        List<ProxyEntity> proxyEntityList =  resultItems.get("proxyList");

        // 入 redis 前先校验是否是代理

        // 多线程校验代理有效性



    }
}
class ProxyValidataTask implements Runnable{

    ProxyEntity proxyEntity;

    public ProxyValidataTask(ProxyEntity proxyEntity) {
        this.proxyEntity = proxyEntity;
    }

    @Override
    public void run() {

        ProxyUtils.ProxyType proxyType = ProxyUtils.ProxyType.HTTP;

        if(StringUtils.isNotBlank(proxyEntity.getProxyType()) && "https".equals(proxyEntity.getProxyType())){
            proxyType = ProxyUtils.ProxyType.HTTPS;
        }

        if(ProxyUtils.validateIp(proxyEntity.getIp(), proxyEntity.getPort(), proxyType)){

            // 入缓存



        }
    }
}



