package com.lstfight.carrieroperatorproxy.service;

import com.lstfight.carrieroperatorproxy.entity.UseRecord;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>流量使用记录业务接口</p?>
 * @author lst
 */

@Service
public interface UseRecordService {

    /**
     * 插入一条使用记录
     * @param record 一条使用记录
     * @return 是否成功标志
     */
    int recordAdd(UseRecord record);

    /**
     *  插入一个集合的流量使用记录
     * @param recordList 流量使用记录集合
     * @return 返回int值判断是否成功
     */
    int recordListAdd(List<UseRecord> recordList);

    /**
     *
     * @return
     */
    double findTotalByMonthAndId();

    /**
     * 查询到某流量卡的总使用量
     * @param id 流量卡卡号
     * @return 总流量记录
     */
    double findTotalByCurrentMonthAndId(int id);

    /**
     * 某一天的流量
     * @param id 流量卡卡号
     * @return 总流量记录
     */
    double findTotalByDayAndId(int id);

    /**
     * 当天使用的流量
     * @param id 流量卡卡号
     * @return 总流量记录
     */
    double findTotalByCurrentDayAndId(int id);



}
