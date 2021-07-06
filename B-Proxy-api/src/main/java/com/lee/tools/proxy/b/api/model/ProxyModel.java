package com.lee.tools.proxy.b.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
public class ProxyModel {
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
     * 上次验证时间
     */
    private Date validDate;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 修改时间
     */
    private Date gmtModified;
}