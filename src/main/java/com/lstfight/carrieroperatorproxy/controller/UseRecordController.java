package com.lstfight.carrieroperatorproxy.controller;

import com.lstfight.carrieroperatorproxy.entity.UseRecord;
import com.lstfight.carrieroperatorproxy.service.UseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

/**
 * @author lst
 */

@RestController
@RequestMapping("/")
public class UseRecordController {

    private final UseRecordService useRecordService;

    @Autowired
    public UseRecordController(UseRecordService useRecordService) {
        this.useRecordService = useRecordService;
    }

    @RequestMapping("/record/{cardId}")
    public String addRecord(@PathVariable(value = "cardId") int cardId) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        int res = useRecordService.recordAdd(new UseRecord(1, cardId, "watchVideo", time, 1.55, 1));
        return ""+res;
    }

    @RequestMapping("/admin")
    public String hell() {
        return "lst";
    }



}
