package com.record.backend.mapping;

import com.record.backend.pojo.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Along
 * @Date: Created in 0:38 2021/9/2
 * 推荐 Mapping
 **/
@Mapper
// @Repository
public interface RecordMapping {
    // add 新的推荐
    int addRecord(Record record);
    // 使用userid获取数据
    @Select("select * from record where user_id=#{user_id} and status=#{status}")
    List<Record> selectByUserIdAndStatus(Integer user_id,Integer status);
}
