package com.lstfight.carrieroperatorproxy.common;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>获取当月第一天工具</p>
 * <p>这里推荐使用了StringBuilder提升性能 因为不需要线程安全控制所以不需要使用StringBuffer</p>
 * @author lst
 * Created on 2018/5/3.
 */
public class MyDate {
    public static String getFirstDayOfMonth() {
        StringBuilder stringBuffer = new StringBuilder(LocalDate.now().toString().substring(0,10));
        stringBuffer.setCharAt(8,'0');
        stringBuffer.setCharAt(9,'1');
        return stringBuffer.toString();
    }

    public static String getCurrentTime() {
        return LocalDateTime.now().toString();
    }
}
