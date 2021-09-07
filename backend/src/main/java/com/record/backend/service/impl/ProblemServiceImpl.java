package com.record.backend.service.impl;

import com.record.backend.mapping.ProblemMapping;
import com.record.backend.pojo.Problem;
import com.record.backend.pojo.Result;
import com.record.backend.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Along
 * @Date: Created in 19:24 2021/9/5
 **/
@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemMapping problemMapping;

    @Override
    public Result problemsTags() {
        List<String> tapsList = problemMapping.selectAllProblemTags();
        Map<String,Object> tagsMap = new HashMap<>();
        tagsMap.put("Tags", tapsList);
        tagsMap.put(tapsList.get(0), problemMapping.selectAllProblemByTags(tapsList.get(0)));
        return new Result(200,"查询成功",tagsMap);
    }

    @Override
    public Result problemsByTags(String tags) {
        Map<String,Object> problemMap = new HashMap<>();
        problemMap.put("problems", problemMapping.selectAllProblemByTags(tags));
        return new Result(200,"查询成功",problemMap);
    }

    @Override
    public Result problemById(Integer problem_id) {
        Problem problem = problemMapping.selectProblemById(problem_id);
        Map<String,Object> problemMap = new HashMap<>();
        problemMap.put("problem", problem);
        return new Result(200,"查询成功",problemMap);
    }
}
