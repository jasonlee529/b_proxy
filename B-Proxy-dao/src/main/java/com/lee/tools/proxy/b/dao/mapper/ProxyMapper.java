package com.lee.tools.proxy.b.dao.mapper;

import com.lee.tools.proxy.b.dao.dataobject.ProxyDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProxyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProxyDO record);

    int insertSelective(ProxyDO record);

    ProxyDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProxyDO record);

    int updateByPrimaryKey(ProxyDO record);

    int updateBatch(List<ProxyDO> list);

    int batchInsert(@Param("list") List<ProxyDO> list);

    ProxyDO findOne();

    int insertOrUpdate(ProxyDO proxyDO);

    List<ProxyDO> findAllVerify(@Param("millis") Long millis);

    void deleteByHostPort(@Param("host") String host, @Param("port") String port);

    List<ProxyDO> findByHostPort(@Param("host") String host, @Param("port") String port);
}