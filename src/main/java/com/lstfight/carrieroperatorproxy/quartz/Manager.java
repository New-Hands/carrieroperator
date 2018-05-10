package com.lstfight.carrieroperatorproxy.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>任务配置类，可以配置多任务.生成jobBean和triggerBean</p>
 * <p>实现配置化表达式</p>
 * @author lst
 *
 */
@Configuration
public class Manager {
    private final String requestCron = "1-8 31/30 8-12,16,0,18-23 ? * *";

    @Bean
    public JobDetail forgeryJobDetail() {
        return JobBuilder.newJob(ForgeryJob.class)
                .withIdentity("forgeryJob").usingJobData("name","lst")
                .storeDurably()
                .build();
    }

    @Bean
    public JobDetail particularJobDetail() {
        return JobBuilder.newJob(ParticularForgeryJob.class)
                .withIdentity("particularJob").usingJobData("name","particular")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger forgeryTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(requestCron);

        return TriggerBuilder.newTrigger()
                .forJob(forgeryJobDetail())
                .withIdentity("forgeryTrigger", "forgery")
                .withSchedule(cronScheduleBuilder)
                .withDescription("一般的流量使用模拟任务")
                .build();
    }

    /**
     * <p>配置一个特殊任务trigger</p>
     * @return 返回对应的实体bean
     */
    @Bean
    public Trigger particularTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(" 0/15 * * ? * *");

        return TriggerBuilder.newTrigger()
                .forJob(particularJobDetail())
                .withIdentity("ParticularTrigger","forgery")
                .usingJobData("name","test")
                .withSchedule(cronScheduleBuilder)
                .withDescription("指定的流量使用模拟任务")
                .build();
    }

}
