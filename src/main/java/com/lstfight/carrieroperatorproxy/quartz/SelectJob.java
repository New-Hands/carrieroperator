package com.lstfight.carrieroperatorproxy.quartz;

import com.lstfight.carrieroperatorproxy.common.MyDate;
import com.lstfight.carrieroperatorproxy.entity.UsedAmount;
import com.lstfight.carrieroperatorproxy.repository.UsedAmountRepository;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lst
 * Created on 2018/5/9.
 */
public class SelectJob extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(SelectJob.class);
    @Autowired
    private UsedAmountRepository usedAmountRepository;

    private static final AtomicInteger START_NUM = new AtomicInteger(1000000001);
    private static final int END_NUM = 1000032768;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        for (; START_NUM.get() <= END_NUM; ) {
            int res = START_NUM.getAndIncrement();
            UsedAmount usedAmount = usedAmountRepository.queryUsedAmountByCardNumberAndRegion(res, MyDate.getFirstDayOfMonth());
            LOGGER.info(usedAmount.toString());
        }
    }
}
