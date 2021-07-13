package com.lee.tools.proxy.b.manager.fetcher;

import com.google.common.collect.Lists;
import com.lee.tools.proxy.b.api.ProxyService;
import com.lee.tools.proxy.b.api.model.ProxyDTO;
import com.lee.tools.proxy.b.manager.ProxyValid;
import com.lee.tools.proxy.b.manager.fetcher.impl.Ip3366ProxyFetcher;
import com.lee.tools.proxy.b.manager.fetcher.impl.JiangXianLiProxyFetcher;
import com.lee.tools.proxy.b.manager.fetcher.impl.Six66ProxyFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Title: FetchManager
 * @Description:
 * @author: libo
 * @date: 2021/7/10 0010 0:44
 * @Version: 1.0
 */
@Slf4j
@Component
public class ProxyManager implements InitializingBean, Serializable {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    @Autowired
    private ProxyService proxyService;

    private List<ProxyFetcher> list = Lists.newArrayList();

    @Override
    public void afterPropertiesSet() throws Exception {
        list.add(new Six66ProxyFetcher());
        list.add(new Ip3366ProxyFetcher());
        list.add(new JiangXianLiProxyFetcher());
    }

    public void fetch() throws BeansException {
        for (ProxyFetcher proxyFetcher : list) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    List<ProxyDTO> modelList = proxyFetcher.fetch();
                    proxyService.saveAll(modelList);
                }
            });
            executorService.submit(thread);
        }
    }

    public void clean() throws BeansException {
        List<ProxyDTO> list = proxyService.findAllVerify(System.currentTimeMillis());
        for (ProxyDTO dto : list) {
            if (!ProxyValid.valid(dto))
                proxyService.degrade(dto.getHost(), dto.getPort());
        }
    }
}