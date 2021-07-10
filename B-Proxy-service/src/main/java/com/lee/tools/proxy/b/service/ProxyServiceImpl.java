package com.lee.tools.proxy.b.service;

import com.google.common.collect.Lists;
import com.lee.tools.proxy.b.api.ProxyService;
import com.lee.tools.proxy.b.api.model.ProxyDTO;
import com.lee.tools.proxy.b.dao.dataobject.ProxyDO;
import com.lee.tools.proxy.b.dao.mapper.ProxyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Component
public class ProxyServiceImpl implements ProxyService {

    @Autowired
    private ProxyMapper proxyMapper;

    private static final BeanCopier copier = BeanCopier.create(ProxyDTO.class, ProxyDO.class, false);
    private static final BeanCopier copier2 = BeanCopier.create(ProxyDO.class, ProxyDTO.class, false);


    @Override
    public ProxyDTO getOne() {
        ProxyDO proxy = proxyMapper.findOne();
        ProxyDTO model = new ProxyDTO();
        if (proxy == null) {
            return null;
        }
        copier2.copy(proxy, model, null);
        return model;
    }

    @Override
    public void saveAll(List<ProxyDTO> modelList) {
        for (ProxyDTO model : modelList) {
            ProxyDO pd = new ProxyDO();
            copier.copy(model, pd, null);
            proxyMapper.insertOrUpdate(pd);
        }
    }

    @Override
    public List<ProxyDTO> findAllVerify(Long millis) {
        List<ProxyDO> list = proxyMapper.findAllVerify(millis);
        List<ProxyDTO> res = Lists.newArrayList();
        for (ProxyDO pd : list) {
            ProxyDTO pdt = new ProxyDTO();
            copier2.copy(pd, pdt, null);
            res.add(pdt);
        }
        return res;
    }

    @Override
    public void deleteByHostPort(ProxyDTO dto) {
        proxyMapper.deleteByHostPort(dto.getHost(), dto.getPort());
    }
}
