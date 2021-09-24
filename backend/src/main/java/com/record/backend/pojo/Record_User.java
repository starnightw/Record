package com.record.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Along
 * @Date: Created in 14:12 2021/9/24
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record_User {
    private Integer id;
    private Integer user_id;
    private Integer u_status; // 用户状态 1 需要推荐 0 不需要推荐
}
