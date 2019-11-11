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
package com.bigguy.spider.entity;

import lombok.Data;

/**
 * @author ：hechen
 * @data ：2019/9/27
 * @description ：
 */
@Data
public class MovieItem {

    private String rank;

    private String movieName;

    /**
     * 链接到详情页面
     */
    private String movieLink;

    private String moviePoster;

    /**
     * 主演
     */
    private String stars;

    /**
     * 上映时间
     */
    private String releaseTime;

}
