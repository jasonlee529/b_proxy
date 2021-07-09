package com.lee.tools.proxy.b.api;


import com.lee.tools.proxy.b.api.model.ProxyModel;

import java.util.List;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
public interface ProxyService {


    ProxyModel getOne();

    void saveAll(List<ProxyModel> modelList);
}
