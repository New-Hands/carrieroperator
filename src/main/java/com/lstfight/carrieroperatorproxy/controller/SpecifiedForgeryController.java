package com.lstfight.carrieroperatorproxy.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * <p>指定模拟控制类 定制卡号使用</p>
 * @author lst
 */
@RestController
@RequestMapping("/custom")
public class SpecifiedForgeryController {

    /**
     * 处理指定卡号的伪造
     * @param numbers 需要伪造的卡号
     * @return 返回处理结果
     */
    public String SpecifiedForgery(@NotNull @PathVariable(value = "numbers") String numbers) {

        return "";
    }
}
