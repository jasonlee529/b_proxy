package com.lee.tools.proxy.b.manager.fetcher;

import com.google.common.collect.Lists;
import com.lee.tools.proxy.b.api.ProxyService;
import com.lee.tools.proxy.b.api.model.ProxyModel;
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
public class FetchManager implements InitializingBean, Serializable {
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    @Autowired
    private ProxyService proxyService;

    private List<ProxyFetcher> list = Lists.newArrayList();

    @Override
    public void afterPropertiesSet() throws Exception {
        list.add(new Six66ProxyFetcher());


    }

    public void fetch() throws BeansException {
        for (ProxyFetcher proxyFetcher : list) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    List<ProxyModel> modelList = proxyFetcher.fetch();
                    proxyService.saveAll(modelList);
                }
            });
            executorService.submit(thread);
        }
    }
}