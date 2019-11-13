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

import com.bigguy.spider.cst.ParamCst;
import com.bigguy.spider.entity.ProxyEntity;
import com.bigguy.spider.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ：hechen
 * @data ：2019/11/13
 * @description ：redis 数据操作服务
 */
public class ProxyService {

    @Autowired
    RedisService redisService;

    public ProxyEntity getByKey(String key){
        String val = (String)redisService.get(key);

        if(val != null){
            ProxyEntity proxyEntity = JSONUtils.jsonParse(val, ProxyEntity.class);
            return proxyEntity;
        }
        return null;
    }

    /**
     * 查出全部的代理
     * @return
     */
    public List<ProxyEntity> getProxyList(){
        redisService.get(ParamCst.RedisCst.ROOT_PATH);

        return null;
    }


}
