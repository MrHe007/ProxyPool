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
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：hechen
 * @data ：2019/11/11
 * @description ：
 */
public class XiCiSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    private final static String URL = "https://www.xicidaili.com/nn/";

    @Override
    public void process(Page page) {

        Html html = page.getHtml();

        List<Selectable> trList = html.xpath("//tr").nodes();

        List<ProxyEntity> proxyEntityList = new ArrayList<>(100);

        int lineIndex = 0;
        for (Selectable item : trList) {
            lineIndex++;
            if (lineIndex == 1){
                continue;
            }

            List<Selectable> oneLine = item.css("td").nodes();
            String ip = oneLine.get(1).xpath("td/text()").get();
            Integer port = Integer.parseInt(oneLine.get(2).xpath("td/text()").get());
            String serverAddr = oneLine.get(3).xpath("td/a/text()").get();
            boolean isAnno = oneLine.get(4).xpath("td/text()").get().contains("匿") ? true : false;
            String proxyType = oneLine.get(5).xpath("td/text()").get();
            String webExipreTime = oneLine.get(8).xpath("td/text()").get();


            ProxyEntity proxyEntity = new ProxyEntity(ip, port, serverAddr, isAnno, proxyType, webExipreTime);
            proxyEntityList.add(proxyEntity);

        }

        page.putField("proxyList", proxyEntityList );

        // 继续爬取下一页

//        String nextUrl = html.css("").get();
//        page.addTargetRequest(nextUrl);

    }

    @Override
    public Site getSite() {
        return this.site;
    }

    public static void main(String[] args) {
        Spider.create(new XiCiSpider())
                .addUrl(URL)
                .addPipeline(new XiciPipeline())
                .thread(5)
                .run();
    }
}