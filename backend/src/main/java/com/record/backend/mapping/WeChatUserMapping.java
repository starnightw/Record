package com.record.backend.mapping;

import com.record.backend.pojo.WeChatUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Along
 * @Date: Created in 22:00 2021/9/1
 * 微信用户 Mapping
 **/
@Mapper
@Repository
public interface WeChatUserMapping {
    // 查询openid是否存在并返回信息
    WeChatUser selectByOpenId(String openid);
    // 查询user_id 是否存在
    WeChatUser selectByUserId(Integer user_id);
    // 添加新的微信用户
    int addWeChatUser(WeChatUser weChatUser);
    // 根据openid保存 他的 user_id
    int updateWeChatUserByOpenidForUserId(String openid, Integer user_id);
    //如果过期 根据openid更新 他的 session_key
    int updateWeChatUserByOpenidForSessionKey(String openid, String session_key);
}
