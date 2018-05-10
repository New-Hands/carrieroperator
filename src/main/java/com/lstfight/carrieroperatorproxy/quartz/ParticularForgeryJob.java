package com.lstfight.carrieroperatorproxy.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * <p>针对性的数据伪造任务</p>
 * <p>??JobExecutionException</p>
 *
 * @author lst
 */
public class ParticularForgeryJob extends QuartzJobBean {
     private static final Logger LOGGER = LoggerFactory.getLogger(ParticularForgeryJob.class);
    private String name;

    public void setName(String name) {
        this.name = name;
    }
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("开始执行特别的模拟任务");
        System.out.println("特别执行"+context.getMergedJobDataMap().getString("name"));
    }
}
