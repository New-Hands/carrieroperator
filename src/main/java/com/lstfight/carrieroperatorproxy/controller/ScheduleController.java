package com.lstfight.carrieroperatorproxy.controller;

import com.lstfight.carrieroperatorproxy.common.Result;
import com.lstfight.carrieroperatorproxy.entity.JobRow;
import com.lstfight.carrieroperatorproxy.entity.UseRecord;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 *
 * <p>实现对任务的控制管理 对流量使用模拟工作进行控制</p>
 * <p>trigger进行的是单一计划的控制 对job是针对该job的所有计划进行功能</p>
 * <p>而scheduler是执行调度者</p>
 *
 * @author lst
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final Scheduler quartzScheduler;

    private final JobDetail particularJobDetail;

    private final Trigger forgeryTrigger;

    /**
     * <p>依赖注入</p>
     * 注入starter自动配置的scheduler
     *
     * @param quartzScheduler starter自动配置的scheduler
     */
    @Autowired
    public ScheduleController(Scheduler quartzScheduler, JobDetail particularJobDetail, Trigger forgeryTrigger) {
        this.quartzScheduler = quartzScheduler;
        this.particularJobDetail = particularJobDetail;
        this.forgeryTrigger = forgeryTrigger;
    }

    /**
     * 使用连接的方式是不用担心参数为空的嘛？？
     *
     * @param jobName 任务名
     * @return 返回暂停任务的结果
     */
    @RequestMapping("pause/{job}")
    public String pause(@NotNull @PathVariable(value = "job") String jobName) {
        try {
            quartzScheduler.pauseJob(new JobKey(jobName));
            return "成功暂停" + jobName;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "false";
        }
    }

    @RequestMapping("resume/{job}")
    public String resume(@PathVariable(value = "job") String jobName) {
        try {
            quartzScheduler.resumeJob(new JobKey(jobName));
            return "成功唤醒";
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "false";
        }
    }

    @RequestMapping("schedule")
    public String scheduleJob() {
        try {
            quartzScheduler.scheduleJob(particularJobDetail, forgeryTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping("test")
    public Result<UseRecord> test() {
        return new Result<UseRecord>().addStatus(1).addMessage("测试");
    }

    @RequestMapping("getGroups")
    public Result getTriggerGroups() throws SchedulerException {
        return new Result<>().addStatus(Result.SUCCESS_CODE).addData(quartzScheduler.getTriggerGroupNames());
    }

    /**
     * @return 计划任务map
     * @throws SchedulerException quartz任务异常
     */
    @RequestMapping("jobSchedule")
    public Result getAllTriggerPlan() throws SchedulerException {
        HashMap<String , List<JobRow>> reeMap = new HashMap<>(16);

        for (String group : quartzScheduler.getTriggerGroupNames()) {
            GroupMatcher<TriggerKey> triggerKeyGroupMatcher = GroupMatcher.triggerGroupEquals(group);
            Set<TriggerKey> triggerKeys = quartzScheduler.getTriggerKeys(triggerKeyGroupMatcher);
            List<JobRow> list = new ArrayList<>();
            for (TriggerKey triggerKey : triggerKeys) {
                CronTrigger trigger = (CronTrigger) quartzScheduler.getTrigger(triggerKey);
                list.add(new JobRow(triggerKey.getName(),
                        triggerKey.getGroup(),
                        trigger.getCronExpression(),
                        quartzScheduler.getTriggerState(triggerKey).toString(),
                        trigger.getDescription()));
            }
            reeMap.put(group, list);
        }

        return new Result<Map<String, List<JobRow>>>().addData(reeMap);
    }

    /**
     *
     * @return 操作结果
     * @throws SchedulerException quartz执行异常
     */
    @RequestMapping("reScheduleJob")
    public String  reScheduleTrigger() throws SchedulerException {

        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * ? * *"))
                .withIdentity(new TriggerKey("test", "monitor"))
                .build();

        quartzScheduler.rescheduleJob(new TriggerKey("6da64b5bd2ee-5ba2006d-8186-4c9a-a5e1-29b1772a11ad", "default"), trigger);
        return  quartzScheduler.getTriggerState(new TriggerKey("test","monitor")).toString();
    }

    @RequestMapping("{code}/{name}/{group}")
    public String control(@PathVariable("code") String code,
                          @PathVariable("name") String name,
                          @PathVariable("group") String group)
            throws SchedulerException {

        Code instruction = Code.valueOf(code.toUpperCase());
        switch (instruction){
            case PAUSE:
                quartzScheduler.pauseTrigger(new TriggerKey(name,group));
                return "";
            case RESUME:
                quartzScheduler.resumeTrigger(new TriggerKey(name,group));
                return "";
            case GETSTATE:
                quartzScheduler.getTriggerState(new TriggerKey(name, group));
                return quartzScheduler.getTriggerState(new TriggerKey(name, group)).toString();
            default:
                return "命令错误";
        }
    }

    enum Code{
        /**
         * 暂停
         */
        PAUSE("pause"),
        /**
         * 唤醒
         */
        RESUME("resume"),

        /**
         * 获取trigger状态
         */
        GETSTATE("getState");

        Code(String state) {
            this.state = state;
        }

        String state;
    }
}
