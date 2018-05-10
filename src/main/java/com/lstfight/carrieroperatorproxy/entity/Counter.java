package com.lstfight.carrieroperatorproxy.entity;

/**
 * <P>访问记录计数器</P>
 * @author lst
 */
public class Counter {
    private int count;

    public int countAdd() {
        return count++;
    }

    public static void main(String[] args) {
        System.out.println(new Counter().countAdd());
    }
}
