package com.lee.tools.proxy.b.api;


import com.lee.tools.proxy.b.api.model.ProxyDTO;

import java.util.List;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
public interface ProxyService {


    ProxyDTO getOne();

    void saveAll(List<ProxyDTO> modelList);

    List<ProxyDTO> findAllVerify(Long millis);

    void deleteByHostPort(ProxyDTO dto);
}
