package com.lstfight.carrieroperatorproxy.service;

import com.lstfight.carrieroperatorproxy.entity.UseRecord;
import com.lstfight.carrieroperatorproxy.repository.UsedRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>流量使用记录业务逻辑类</p>
 * <P>提供伪造流量使用的方法</P>
 * @author lst
 * @// TODO: 2018/4/14 选择 是在记录里查 还是生成报表在报表里进行查询
 */

@Service
public class UseRecordServiceImpl implements UseRecordService {

    /**
     * final关键字可保证对象的在多线程间的安全初始化
     */
    private final UsedRecordRepository usedRecordRepository;

    /**
     * 使用构造方法的方式进行依赖注入
     * @param usedRecordRepository dao层依赖
     */
    @Autowired
    UseRecordServiceImpl(UsedRecordRepository usedRecordRepository) {
        this.usedRecordRepository = usedRecordRepository;
    }

    @Override
    public int recordListAdd(List<UseRecord> recordList) {
        return 0;
    }

    @Override
    public int recordAdd(UseRecord record) {
        int res = usedRecordRepository.save(record);
        return 0;
    }

    @Override
    public double findTotalByMonthAndId() {
        return 0;
    }

    @Override
    public double findTotalByCurrentMonthAndId(int id) {
        return 0;
    }

    @Override
    public double findTotalByDayAndId(int id) {
        return 0;
    }

    @Override
    public double findTotalByCurrentDayAndId(int id) {
        return 0;
    }
}
