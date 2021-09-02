package com.record.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Along
 * @Date: Created in 22:30 2021/9/1
 * 简单的User
 * id  username password nickname
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String nickname;
    private String password;
}
