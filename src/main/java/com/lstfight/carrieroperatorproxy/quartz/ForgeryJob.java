package com.lstfight.carrieroperatorproxy.quartz;

import com.lstfight.carrieroperatorproxy.common.MyDate;
import com.lstfight.carrieroperatorproxy.entity.UsedAmount;
import com.lstfight.carrieroperatorproxy.repository.UsedAmountRepository;
import org.quartz.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * <p>定义伪造流量使用记录的具体工作内容</p>
 * <p>根据什么来伪造使用记录</p>
 *
 * @author lst
 */
public class ForgeryJob extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForgeryJob.class);
    private static final AtomicInteger START_NUM = new AtomicInteger(1000000001);
    private static final int END_NUM = 1000032768;

    /**
     * 这里采用这样的方式进行注入 因为job的初始化需要调用无参的构造函数
     */
    @Autowired
    private UsedAmountRepository usedAmountRepository;

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        int key;
        for (; (key = START_NUM.getAndIncrement()) <= END_NUM; ) {
            double usedAmount = Math.random();
            LOGGER.info("开始执行日常模拟计划任务" + MyDate.getCurrentTime() + key);
            usedAmountRepository.updateUsedAmount(new UsedAmount(0, key, usedAmount, MyDate.getFirstDayOfMonth()));
        }
    }
}
