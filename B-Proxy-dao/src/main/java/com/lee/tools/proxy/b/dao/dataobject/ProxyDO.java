package com.lee.tools.proxy.b.dao.dataobject;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

/**
    * 代理表
    */
@Data
public class ProxyDO {
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