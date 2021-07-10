package com.lee.tools.proxy.b.service;

import com.google.common.collect.Lists;
import com.lee.tools.proxy.b.api.ProxyService;
import com.lee.tools.proxy.b.api.model.ProxyDTO;
import com.lee.tools.proxy.b.dao.dataobject.ProxyDO;
import com.lee.tools.proxy.b.dao.mapper.ProxyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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
            List<ProxyDO> pds = proxyMapper.findByHostPort(model.getHost(), model.getPort());
            if (CollectionUtils.isEmpty(pds)) {
                ProxyDO pd = new ProxyDO();
                copier.copy(model, pd, null);
                proxyMapper.insertOrUpdate(pd);
            }
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

    @Override
    public boolean degrade(String host, String port) {
        List<ProxyDO> pds = proxyMapper.findByHostPort(host, port);
        if (!CollectionUtils.isEmpty(pds)) {
            ProxyDO pd = pds.get(0);
            if (pd.getQuality().intValue() <= 1) {
                proxyMapper.deleteByHostPort(host, port);
            } else {
                pd.setQuality(pd.getQuality() - 1);
                pd.setNextVerifyTime(System.currentTimeMillis() + 1000 * 60 * 60 * 6);
                proxyMapper.updateByPrimaryKeySelective(pd);
            }
        }
        return true;
    }
}
