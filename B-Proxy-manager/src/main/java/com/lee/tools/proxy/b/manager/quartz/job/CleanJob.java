package com.lee.tools.proxy.b.manager.quartz.job;

import com.lee.tools.proxy.b.manager.fetcher.ProxyManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Title: CleanJob
 * @Description:
 * @author: libo
 * @date: 2021/7/10 0010 11:20
 * @Version: 1.0
 */
@Slf4j
public class CleanJob extends QuartzJobBean {
    private ProxyManager proxyManager;

    private String name;

    @SneakyThrows
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        proxyManager = (ProxyManager) jobExecutionContext.getScheduler().getContext().get("fetchManager");
        proxyManager.clean();
        log.info("------springboot quartz CleanJob: ###############" + jobExecutionContext.getTrigger());
    }
}