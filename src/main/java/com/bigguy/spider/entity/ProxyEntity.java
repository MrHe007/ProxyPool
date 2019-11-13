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
 * @data ：2019/11/11
 * @description ：代理
 */
@Data
public class ProxyEntity {

    private String ip;

    private Integer port;

    /**
     * 代理服务器地址
     */
    private String serverAddr;

    /**
     * 是否匿名
     */
    private boolean isAnno;

    /**
     * 代理类型：HTTP、HTTPS
     */
    private String proxyType;

    /**
     * 网页中的存活时间
     */
    private String webExipreTime;

    /**
     * 存活时间戳
     */
    private Long exipreTime;

    /**
     * 代理分数：100,
     */
    private Integer score;

    public ProxyEntity(String ip, Integer port, String serverAddr, boolean isAnno, String proxyType, String webExipreTime) {
        this.ip = ip;
        this.port = port;
        this.serverAddr = serverAddr;
        this.isAnno = isAnno;
        this.proxyType = proxyType;
        this.webExipreTime = webExipreTime;
    }
}
