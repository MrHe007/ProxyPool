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

import com.bigguy.spider.spider.xici.XiCiSpider;
import com.bigguy.spider.spider.xici.XiciPipeline;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;

/**
 * @author ：hechen
 * @data ：2019/11/13
 * @description ：
 */
@RestController
@RequestMapping("/spider")
public class SpiderController {

    @GetMapping("/xici")
    public void xiciSpider(){
        String url = "https://www.xicidaili.com/nn/";
        Spider.create(new XiCiSpider())
                .addUrl(url)
                .addPipeline(new XiciPipeline())
                .thread(5)
                .run();

    }

}
