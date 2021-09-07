package com.record.backend.mapping;

import com.record.backend.pojo.EveryDayTeach;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Along
 * @Date: Created in 1:04 2021/9/7
 **/
@Mapper
@Repository
public interface EveryDayTeachMapping {

    List<EveryDayTeach> selectAllEveryDayTeach();
}
