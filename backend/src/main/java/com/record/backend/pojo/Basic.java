package com.record.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Along
 * @Date: Created in 23:35 2021/9/24
 * 基础信息的设置保存
 *      例如：贡献 成果 domjudge 私云
 *      简述：
 *      详述:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Basic {
    private Integer id;
    private String name; // 名称
    private String recordContent;  // markdown内容  作为展示记录
    private String synopsisContent; // markdown的主要内容
}
