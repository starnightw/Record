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
public class Solution implements Comparable<Solution>{
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
    public Integer score; // 分数
    private Integer created_by; //相对应用户id

    // private Problem problem;

    public Solution(int problem_id, int score) {
        this.problem_id = problem_id;
        this.score = score;
    }

    public int compareTo(Solution o) {
        return problem_id < o.problem_id ? -1 : 1;
    }

}
