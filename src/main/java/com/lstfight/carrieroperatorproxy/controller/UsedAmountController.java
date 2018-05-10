package com.lstfight.carrieroperatorproxy.controller;

import com.lstfight.carrieroperatorproxy.common.MyDate;
import com.lstfight.carrieroperatorproxy.common.Result;
import com.lstfight.carrieroperatorproxy.entity.UsedAmount;
import com.lstfight.carrieroperatorproxy.repository.UsedAmountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @author lst
 * Created on 2018/5/3.
 */
@RestController
@RequestMapping("/useAmount")
public class UsedAmountController {

    private final UsedAmountRepository usedAmountRepository;

    @Autowired
    public UsedAmountController(UsedAmountRepository usedAmountRepository) {
        this.usedAmountRepository = usedAmountRepository;
    }

    /**
     * 用户使用流量模拟
     * @param usedAmount 模拟的使用流量
     * @return 操作结果
     */
    @RequestMapping("/used/{cardNumber}/{usedAmount}")
    public Result usedAmountUpdate(@Valid UsedAmount usedAmount) {

        usedAmountRepository.updateUsedAmount(new UsedAmount(0,usedAmount.getCardNumber(),usedAmount.getUsedAmount(),MyDate.getFirstDayOfMonth()));
        return new Result().addStatus(Result.SUCCESS_CODE).addMessage(MyDate.getFirstDayOfMonth());
    }

    /**
     * 查询已经使用的力量
     * @param usedAmount 需要查询的卡号
     * @return 查询结果
     */
    @RequestMapping("/query/{cardNumber}")
    public Result query(@Valid UsedAmount usedAmount) {
        UsedAmount usedAmountRes = usedAmountRepository.queryUsedAmountByCardNumberAndRegion(usedAmount.getCardNumber(),MyDate.getFirstDayOfMonth());
        if (usedAmountRes == null ) {
            return new Result().addStatus(Result.FAIL_CODE).addMessage("false");
        }
        return new Result<UsedAmount>().addStatus(Result.SUCCESS_CODE).addData(usedAmountRes);
    }
}
