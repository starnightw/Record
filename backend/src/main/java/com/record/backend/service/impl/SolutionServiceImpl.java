package com.record.backend.service.impl;

import com.record.backend.pojo.Result;
import com.record.backend.service.SolutionService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: Along
 * @Date: Created in 15:36 2021/9/6
 **/
@Service
public class SolutionServiceImpl implements SolutionService {
    @Override
    public Result selectByUserId(Integer user_id) {
        return null;
    }

    @Override
    public Result selectByUserIdAndScore(Integer user_id, Integer score) {
        return null;
    }

    @Override
    public Result selectByFromFirstTimeToLastTime(Integer user_id, Date firstDayDate, Date LastDayDate) {
        return null;
    }
}
