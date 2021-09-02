package com.record.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: Along
 * @Date: Created in 23:44 2021/9/1
 * 运行结果
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Solution {
    private Integer id;
    private Integer problem_id;
    private Integer time;
    private Integer memory; //占用内存
    private Date created_at; // 运行时间
    private Integer result; // 结果
    private Integer language;
    private Integer code_length; // 代码长度
    private Date judgetime; // 判题机运行的时间
    private String pass_info; // 通过率
    private Integer score; // 分数
    private Integer created_by; //相对应用户id

}
