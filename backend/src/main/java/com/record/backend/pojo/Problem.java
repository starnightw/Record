package com.record.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Along
 * @Date: Created in 23:26 2021/9/1
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Problem {
    private Integer id;  //题号
    private String title;  //标题
    private Integer spj;  //是否为spj
    private String description;  // 内容
    private Integer time_limit;  //时间限制
    private Integer memory_limit;  //空间限制
    private String input;  // 输入格式
    private String output;  //输出格式
    private String sample_input;  // 样例输入
    private String sample_output;  // 样例输出
    private String tags;  // tags标签
    private String hint;  // hint提示
    private String solution; // 题解
}
