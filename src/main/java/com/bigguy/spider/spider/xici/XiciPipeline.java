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

    @Override
    public void process(ResultItems resultItems, Task task) {

        String url = resultItems.getRequest().getUrl();
        System.out.println(url);
        List<ProxyEntity> proxyEntityList =  resultItems.get("proxyList");

        for (ProxyEntity proxyEntity : proxyEntityList) {

            System.out.println(proxyEntity);
        }

    }
}
