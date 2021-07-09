package com.lee.tools.proxy.b.manager.fetcher;


import com.lee.tools.proxy.b.api.model.ProxyModel;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * 爬取远程Proxy的接口
 */
public interface ProxyFetcher extends Callable<List<ProxyModel>> {

    public List<ProxyModel> fetch();

    public void setUrls(String[] urls);
}
