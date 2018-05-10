package com.lstfight.carrieroperatorproxy.repository;

import com.lstfight.carrieroperatorproxy.entity.UsedAmount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * @author lst
 * Created on 2018/5/3.
 */
@Mapper
@Component
public interface UsedAmountRepository {
    /**
     * <p><p/>
     * @param usedAmount canshu
     * @return 返回状态
     */
    @Update("update used_amount set used_amount = used_amount + #{usedAmount} where card_number = #{cardNumber} and region like #{region}")
    int updateUsedAmount(UsedAmount usedAmount);

    /**
     * dsfd
     * @param cardNumber 卡号
     * @param region 指定实践段
     * @return 返回使用的记录
     */
    @Select("select card_number, used_Amount from used_amount where card_number = #{cardNumber} and region = #{region}")
    UsedAmount queryUsedAmountByCardNumberAndRegion(@Param(value = "cardNumber") int cardNumber, @Param("region") String region);
}
