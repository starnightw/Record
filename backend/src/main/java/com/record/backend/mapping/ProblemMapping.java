package com.record.backend.mapping;

import com.record.backend.pojo.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    Problem selectProblemById(Integer id);

    // 不重复的查询出所有的tags
    List<String> selectAllProblemTags();

    List<Problem> selectAllProblemByTags(String tags);

}
