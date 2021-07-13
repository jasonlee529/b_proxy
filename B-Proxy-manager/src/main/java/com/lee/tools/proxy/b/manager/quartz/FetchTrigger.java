package com.lee.tools.proxy.b.manager.quartz;

import com.lee.tools.proxy.b.manager.fetcher.ProxyManager;
import com.lee.tools.proxy.b.manager.quartz.job.CleanJob;
import com.lee.tools.proxy.b.manager.quartz.job.FetchJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: FetchTrigger
 * @Description:
 * @author: libo
 * @date: 2021/7/10 0010 2:17
 * @Version: 1.0
 */
@Slf4j
@Service
public class FetchTrigger implements InitializingBean {
    @Autowired
    private ProxyManager proxyManager;
    @Autowired
    private QuartzService quartzService;

    @Override
    public void afterPropertiesSet() throws Exception {
        Long mills = System.currentTimeMillis();
        Map context = new HashMap<>();
        context.put("fetchManager", proxyManager);
        Map detail = new HashMap<>();
        detail.put("name", "fetchProxy");
        quartzService.addJob(FetchJob.class, String.format("fetch_%d",mills), "proxy", "0 */30 * * * ?", context, detail);
        detail.put("name", "cleanProxy");
        quartzService.addJob(CleanJob.class,  String.format("clean_%d",mills), "proxy", "0 */10 * * * ?", context, detail);
    }
}