package com.lee.tools.proxy.b.manager.quartz;

import com.lee.tools.proxy.b.manager.fetcher.FetchManager;
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
        Map map = new HashMap<>();
        map.put("fetchManager", fetchManager);
        quartzService.addJob(FetchJob.class, "fetch", "fetch", "0 */5 * * * ?", map, null);
    }
}