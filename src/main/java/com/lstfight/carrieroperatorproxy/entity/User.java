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
public class User {
    private int id;
    private String name;
    private int status;
}
