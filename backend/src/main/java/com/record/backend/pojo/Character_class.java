package com.record.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Along
 * @Date: Created in 0:32 2021/9/25
 * 人物志类别
 *      charaClass: String, // 分类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character_class {
    private Integer id;
    private String className;
}
