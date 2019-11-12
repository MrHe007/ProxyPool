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
package com.bigguy.spider.controller;

import com.bigguy.spider.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：hechen
 * @data ：2019/9/27
 * @description ：健康检查
 */
@RestController
public class CommonController {

    @Autowired
    RedisService redisService;

    @GetMapping("/healthCheck")
    public Object healthCheck(){
        redisService.set("name","hechen");

        return "ok";
    }

}
