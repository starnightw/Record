package com.record.backend.mapping;

import com.record.backend.pojo.ContestView;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Along
 * @Date: Created in 19:41 2021/9/4
 **/
@Mapper
@Repository
public interface ContestViewMapping {
    // 使用type查询该类型下的所有比赛
    List<ContestView> selectAllContestViewByType(Integer type);
}
