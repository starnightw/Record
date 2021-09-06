package com.record.backend.service;

import com.record.backend.pojo.Result;

/**
 * @Author: Along
 * @Date: Created in 10:10 2021/9/4
 **/
public interface WeChatLoginService {

    Result WeChatLogin(String code);

    Boolean WeChatIfLogin(String code, String token);
}
