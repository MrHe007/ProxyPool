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
package com.bigguy.spider.spider;

import com.bigguy.spider.entity.MovieItem;
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
 * @data ：2019/9/27
 * @description ：
 */
public class UserSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    private final static String URL = "https://maoyan.com/board/4";

    @Override
    public void process(Page page) {

        Html html = page.getHtml();

        List<Selectable> movieItemList = html.$("dl.board-wrapper").css("dd").nodes();
        List<MovieItem> movieList = new ArrayList<>();
        movieItemList.forEach(item -> {
            MovieItem movieItem = new MovieItem();
            String movieRank = item.xpath("i[@class='board-index']").get();
            Selectable aSel = item.xpath("//a[@class='image-link']");
            String s = aSel.xpath("/a/@href").get();
            String title = aSel.xpath("/a/@title").get();
            String imgUrl = aSel.xpath("//img[@class='board-img']/@src").get();

            Selectable movieInfoSel = item.xpath(".//div[@class='movie-item-info']");
            String movieName = movieInfoSel.xpath("//p[@class='name']/text()").get();
            String stars = movieInfoSel.xpath("//p[@class='star']").get();

            String movieScore = item.xpath("//p[@class='score']").get();

            movieItem.setMovieLink("");

        });


    }

    @Override
    public Site getSite() {
        return this.site;
    }

    public static void main(String[] args) {
        Spider.create(new UserSpider())
                .addUrl(URL)
                .thread(5)
                .run();
    }
}
