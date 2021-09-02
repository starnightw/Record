package com.record.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Along
 * @Date: Created in 0:33 2021/9/2
 * 推荐
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private Integer id;
    private Integer user_id;
    private Integer problem_id;
    private Integer status; // 状态 0 没打开 1 打开了
}
