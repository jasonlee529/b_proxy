package com.lee.tools.proxy.b.manager.quartz;

import com.lee.tools.proxy.b.manager.fetcher.FetchManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Title: FetchJob
 * @Description:
 * @author: libo
 * @date: 2021/7/10 0010 2:04
 * @Version: 1.0
 */
@Slf4j
public class FetchJob extends QuartzJobBean {
    private FetchManager fetchManager;

    public void setFetchManager(FetchManager fetchManager) {
        this.fetchManager = fetchManager;
    }

    @SneakyThrows
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        fetchManager = (FetchManager) jobExecutionContext.getScheduler().getContext().get("fetchManager");
        fetchManager.fetch();
        log.info("------springbootquartzonejob执行" + jobDataMap.get("name").toString() + "###############" + jobExecutionContext.getTrigger());
    }
}