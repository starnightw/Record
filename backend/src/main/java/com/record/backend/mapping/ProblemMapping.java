package com.record.backend.mapping;

import com.record.backend.pojo.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Along
 * @Date: Created in 23:40 2021/9/1
 * 题目Mapping
 **/
@Mapper
@Repository
public interface ProblemMapping {
    // 查询所有的题目
    List<Problem> selectAllProblem();
    // 使用problem id 查询 并返回内容
    Problem selectByProblemId(Integer id);

}
