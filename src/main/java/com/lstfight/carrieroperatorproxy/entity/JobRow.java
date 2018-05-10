package com.lstfight.carrieroperatorproxy.entity;

import lombok.*;

/**
 * @author lst
 * Created on 2018/5/6.
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobRow {
    @Getter
    @Setter
    private String jobName;

    @Getter
    @Setter
    private String jobGroup;

    @Getter
    @Setter
    private String cronExpression;

    @Getter
    @Setter
    private String state;

    @Getter
    @Setter
    private String description;
}
