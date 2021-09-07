package com.record.backend.service.impl;

import com.record.backend.mapping.SolutionMapping;
import com.record.backend.pojo.Result;
import com.record.backend.pojo.Solution;
import com.record.backend.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Along
 * @Date: Created in 15:36 2021/9/6
 **/
@Service
public class SolutionServiceImpl implements SolutionService {

    @Autowired
    private SolutionMapping solutionMapping;

    // 查询十条信息
    @Override
    public Result selectByUserId(Integer user_id) {
        List<Solution> solutionList = solutionMapping.selectByUserId(user_id);
        Map<String,Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("solutions", solutionList);
        return new Result(200,"获取成功",stringObjectHashMap);
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
