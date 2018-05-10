package com.lstfight.carrieroperatorproxy.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * @author lst
 */

@Component
@Mapper
public interface CardRepository {

    /**
     * 暂停某卡服务
     * @param cardNumber 卡号
     */
    @Update("update iot_card set status = 0 where card_number = #{cardNumber}")
    void pauseCard(int cardNumber);

    /**
     * 激活某卡服务
     * @param cardNumber 卡号
     */
    @Update("update iot_card set status = 1 where card_number = #{cardNumber}")
    void resumeCard(int cardNumber);

    /**
     * 查询某卡使用状态
     * @param cardNumber 卡号
     * @return 状态
     */
    @Select("select status from iot_card where card_number = #{cardNumber}")
    int queryStatusByCardNumber(int cardNumber);
}
