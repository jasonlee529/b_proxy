package com.lee.tools.proxy.b.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: ProxyModel
 * @Description:
 * @author: libo
 * @date: 2021/7/7 0007 1:13
 * @Version: 1.0
 */
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProxyDTO implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 地址
     */
    private String host;

    /**
     * quality
     * 端口
     */
    private String port;

    /**
     * http/https
     */
    private String type;
    /**
     * 地域
     */
    private String region;

    /**
     * 匿名
     */
    private String anonymous;

    /**
     * 质量
     */
    private int quality;
    /**
     * 爬取来源
     */
    private String source;
    /**
     * 上次验证时间
     */
    private Date validDate;
    /**
     * 下一次验证时间
     */
    private Long nextVerifyTime;
    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 修改时间
     */
    private Date gmtModified;
}