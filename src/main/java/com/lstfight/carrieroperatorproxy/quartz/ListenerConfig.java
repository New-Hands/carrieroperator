package com.lstfight.carrieroperatorproxy.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lst
 * Created on 2018/5/6.
 */
@Configuration
public class ListenerConfig {

    private final Scheduler schedulerFactoryBean;

    @Autowired
    public ListenerConfig(Scheduler schedulerFactoryBean) {
        this.schedulerFactoryBean = schedulerFactoryBean;
    }

    @Bean
    public TriggerListener normalListener() throws SchedulerException {

        NormalListener normalListener = new NormalListener();
        schedulerFactoryBean.getListenerManager().addTriggerListener(normalListener);
        return normalListener;
    }
}
