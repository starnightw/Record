package com.record.backend.mapping;

import com.record.backend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: Along
 * @Date: Created in 22:50 2021/9/1
 * 用户 Mapping
 **/
@Mapper
@Repository
public interface UserMapping {
    // 查询username是否存在并返回信息
    User selectByUserId(Integer id);

    User selectByUserName(String username);

    List<User> getAllSolutionAsUsers();
}
