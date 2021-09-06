package com.record.backend.service;

import com.record.backend.pojo.Result;
import com.record.backend.pojo.Solution;

import java.util.Date;
import java.util.List;

/**
 * @Author: Along
 * @Date: Created in 15:34 2021/9/6
 **/
public interface SolutionService {

    // 使用userid获取数据
    Result selectByUserId(Integer user_id);
    // 使用userid获取数据和分数
    Result selectByUserIdAndScore(Integer user_id,Integer score);
    // 使用userid按照时间范围获取数据  月份
    Result selectByFromFirstTimeToLastTime(Integer user_id, Date firstDayDate, Date LastDayDate);
}
