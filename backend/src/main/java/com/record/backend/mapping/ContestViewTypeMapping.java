package com.record.backend.mapping;

import com.record.backend.pojo.ContestViewType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Along
 * @Date: Created in 19:40 2021/9/4
 **/
@Mapper
@Repository
public interface ContestViewTypeMapping {

    // 查询所有的比赛来源
    List<ContestViewType> selectAllContestViewType();
}
