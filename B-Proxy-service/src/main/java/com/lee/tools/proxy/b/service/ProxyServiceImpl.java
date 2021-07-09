package com.lee.tools.proxy.b.service;

import com.lee.tools.proxy.b.api.ProxyService;
import com.lee.tools.proxy.b.api.model.ProxyModel;
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

    private static final BeanCopier copier = BeanCopier.create(ProxyModel.class, ProxyDO.class, false);
    private static final BeanCopier copier2 = BeanCopier.create(ProxyDO.class, ProxyModel.class, false);


    @Override
    public ProxyModel getOne() {
        ProxyDO proxy = proxyMapper.findOne();
        ProxyModel model = new ProxyModel();
        if (proxy == null) {
            return null;
        }
        copier2.copy(proxy, model, null);
        return model;
    }

    @Override
    public void saveAll(List<ProxyModel> modelList) {
        for (ProxyModel model : modelList) {
            ProxyDO pd = new ProxyDO();
            copier.copy(model, pd, null);
            proxyMapper.insertOrUpdate(pd);
        }
    }
}
