package com.lstfight.carrieroperatorproxy.quartz;

import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 * @author lst
 * Created on 2018/5/4.
 */
public class TriggerJobHome {
    private static final Map<String,String> triggerMap = new HashMap();

    public static String addCache(String name, String group) {
       return triggerMap.put(name, group);
    }

}
