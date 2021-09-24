package com.record.backend.mapping;

import com.record.backend.pojo.Record;
import com.record.backend.pojo.Record_User;
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
@Repository
public interface RecordMapping {
    // 查询到所有需要进行推荐的用户
    List<Record_User> selectAllRecordUser(Integer u_status);
    // 订购推荐
    boolean addUserInRecord(Integer user_id);
    // 取消推荐
    boolean noUserInRecord(Integer user_id);
    // add 新的推荐
    boolean addRecord(List<Record> recordList);
    // 使用userid获取数据 根据状态获取
    List<Record> selectByUserIdAndStatus(Integer user_id,Integer u_status);
    // 用户打开了题目 修改状态
    boolean openProblem(Integer id);
}
