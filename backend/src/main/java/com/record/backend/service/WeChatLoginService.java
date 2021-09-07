package com.record.backend.service;

import com.record.backend.pojo.Result;

/**
 * @Author: Along
 * @Date: Created in 10:10 2021/9/4
 **/
public interface WeChatLoginService {

    Result WeChatLogin(String code);

    Result WechatLoginAddUserId(String openId, String username);

    Boolean WeChatIfLogin(String openId, String token);
}
