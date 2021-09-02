package com.record.backend.mapping;

import com.record.backend.pojo.Solution;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author: Along
 * @Date: Created in 0:17 2021/9/2
 * 判题结果Mapping
 * 可以得到可供分析的数据 + 通过的题目
 **/
@Mapper
@Repository
public interface SolutionMapping {
    // 使用userid获取数据
    List<Solution> selectByUserId(Integer user_id);
    // 使用userid获取数据和分数
    List<Solution> selectByUserIdAndScore(Integer user_id,Integer score);
    // 使用userid按照时间范围获取数据  月份
    List<Solution> selectByFromFirstTimeToLastTime(Integer user_id,Date firstDayDate,Date LastDate);
}
