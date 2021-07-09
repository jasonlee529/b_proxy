package com.lee.tools.proxy.b.dao.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 代理表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProxyDO implements Serializable {
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
    private Integer quality;

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

    private static final long serialVersionUID = 1L;
}