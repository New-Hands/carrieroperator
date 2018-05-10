package com.lstfight.carrieroperatorproxy.repository;

import com.lstfight.carrieroperatorproxy.entity.UseRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


import java.sql.Timestamp;
import java.util.List;

/**
 * <p>流量记录dao类</p>
 * @author lst
 */
@Component
@Mapper
public interface UsedRecordRepository {

    /**
     * 向数据库添加一条流量卡使用记录
     * @param useRecord 使用记录的对象
     * @return 返回int值代表结果
     */
    @Insert("insert into used_record(card_id, used_way, used_time, used_amount, status) " +
            "values(#{cardId}, #{usedWay}, #{usedTime,jdbcType=TIMESTAMP}, #{usedAmount}, #{status})")
    int save(UseRecord useRecord);

    /**
     * 添加多条使用记录
     * @param useRecords 使用记录
     * @return 插入结果标志
     */
    @InsertProvider(type = BatchInsertDao.class,method = "listBatchInsert")
    int saveListUseRecord(List<UseRecord> useRecords);


    /**
     * 查询单卡某时间区间内使用流量总计，同过时间区间可以构造每天或每月等的流量使用统计
     * @param cardNumber  流量卡卡号
     * @param statTime 开始时间
     * @param endTime 结束实践
     * @return 返回某一时段内某卡的使用流量总计
     */
    @Select("select count(used_amount) from used_record where card_number = #{cardId} and used_time in(#{startTime},#{endTime}) ")
    double findTotalByTimeRegionAndNumber(int cardNumber, Timestamp statTime, Timestamp endTime);

    /**
     * 查询卡当月已使用流量  都是某一时段 但是参数是更有意义的
     * ？？？这里的参数的抽象可以到service里去解决
     * @param cardNumber 卡号
     * @param startDay 开始时间
     * @param endDay 结束时间
     * @return
     */
/*    @Select("select count(used_amount) from used_record where card_number = #{cardId} and used_time in(#{startTime},#{endTime}) ")
    double findTotalByMonthAndNumber(int cardNumber, Timestamp startDay, Timestamp endDay);*/
}
