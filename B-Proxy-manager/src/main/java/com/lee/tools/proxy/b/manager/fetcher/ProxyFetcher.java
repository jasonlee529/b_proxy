package com.lee.tools.proxy.b.manager.fetcher;


import com.lee.tools.proxy.b.api.model.ProxyDTO;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * 爬取远程Proxy的接口
 */
public interface ProxyFetcher {

    public List<ProxyDTO> fetch();
}
