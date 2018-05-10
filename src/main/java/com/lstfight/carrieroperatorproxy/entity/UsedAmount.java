package com.lstfight.carrieroperatorproxy.entity;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * <p></p>
 * @author lst
 * Created on 2018/5/3.
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsedAmount {
    @Setter @Getter private int id;

    @Setter @Getter
    @Min(value = 1000000001)
    @Max(value = 1000032768)
    private int cardNumber;
    @Setter @Getter private double usedAmount;
    @Setter @Getter private String region;
}
