package com.record.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Along
 * @Date: Created in 21:06 2021/9/1
 * 微信用户表
 **/
@Data
@AllArgsConstructor  //有参
@NoArgsConstructor  //无参
public class WeChatUser {
    private Integer id;
    private String nike;
    private String openid;
    private String session_key;
    private String sex;
    private Integer user_id;
}
