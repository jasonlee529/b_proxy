package com.lee.tools.proxy.b.dao.mapper;

import com.lee.tools.proxy.b.dao.dataobject.ProxyDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProxyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProxyDO record);

    int insertSelective(ProxyDO record);

    ProxyDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProxyDO record);

    int updateByPrimaryKey(ProxyDO record);

    ProxyDO findOne();
}