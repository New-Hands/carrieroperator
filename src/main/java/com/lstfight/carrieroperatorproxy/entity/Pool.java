package com.lstfight.carrieroperatorproxy.entity;

import lombok.*;

/**
 * @author lst
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pool {
    private int id;
    private int ownerId;
    private double restAmount;
    private int usedAmount;
    private int status;
}
