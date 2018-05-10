package com.lstfight.carrieroperatorproxy.entity;

import lombok.*;

import java.sql.Timestamp;

/**
 * <P>流量使用记录实体模型</P>
 * @author lst
 */

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@ToString
public class UseRecord {
    @Getter @Setter private int id;
    @Getter @Setter private int cardId;
    @Getter @Setter private String usedWay;
    @Getter @Setter private Timestamp usedTime;
    @Getter @Setter private double usedAmount;
    @Getter @Setter private int status;

}
