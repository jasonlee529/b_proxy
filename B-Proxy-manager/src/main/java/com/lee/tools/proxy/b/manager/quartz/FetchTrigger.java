package com.lee.tools.proxy.b.manager.quartz;

import com.lee.tools.proxy.b.manager.fetcher.FetchManager;
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
    private FetchManager fetchManager;
    @Autowired
    private QuartzService quartzService;

    @Override
    public void afterPropertiesSet() throws Exception {
        Map context = new HashMap<>();
        context.put("fetchManager", fetchManager);
        Map detail = new HashMap<>();
        detail.put("name", "fetchProxy");
        quartzService.addJob(FetchJob.class, "fetch", "proxy", "0 */10 * * * ?", context, detail);
        detail.put("name", "cleanProxy");
        quartzService.addJob(CleanJob.class, "clean", "proxy", "0 */3 * * * ?", context, detail);
    }
}