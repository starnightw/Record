package com.record.backend.service.impl;

import com.record.backend.mapping.ContestViewMapping;
import com.record.backend.mapping.ContestViewTypeMapping;
import com.record.backend.mapping.EveryDayTeachMapping;
import com.record.backend.pojo.ContestViewType;
import com.record.backend.pojo.EveryDayTeach;
import com.record.backend.pojo.Result;
import com.record.backend.service.ContestViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Along
 * @Date: Created in 0:30 2021/9/6
 **/
@Service
public class ContestViewServiceImpl implements ContestViewService {

    @Autowired
    private ContestViewTypeMapping contestViewTypeMapping;

    @Autowired
    private ContestViewMapping contestViewMapping;

    @Autowired
    private EveryDayTeachMapping everyDayTeachMapping;

    @Override
    public Result getAllContestViewByType() {

        List<ContestViewType> typeList = contestViewTypeMapping.selectAllContestViewType();
        Map<String,Object> contestMap = new HashMap<>();
        // contestMap.put("types",typeList);

        for (ContestViewType contestViewType : typeList) {
            System.out.println(contestViewMapping.selectAllContestViewByType(contestViewType.getId()));
            contestMap.put(contestViewType.getName(),contestViewMapping.selectAllContestViewByType(contestViewType.getId()));
        }

        return new Result(200,"获取成功",contestMap);
    }

    @Override
    public Result getAllEveryDayTeach() {
        List<EveryDayTeach> everyDayTeachList = everyDayTeachMapping.selectAllEveryDayTeach();
        Map<String,Object> everyDayTeachMap = new HashMap<>();
        everyDayTeachMap.put("everyDayTeach",everyDayTeachList);
        return new Result(200,"获取成功",everyDayTeachMap);
    }
}
