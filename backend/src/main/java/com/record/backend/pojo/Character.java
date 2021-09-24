package com.record.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Along
 * @Date: Created in 0:31 2021/9/25
 * 人物志
 *     chara_id: int, // 分类
 *     nameTitle : String,  // 名称 例如： 17-3 王灿
 *     imgUrl : String,  // img 路径
 *     recordContent : String  // markdown内容  作为展示记录
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character {
    private Integer id;
    private Integer class_id;
    private String nameTitle;
    private String imgUrl;
    private String recordContent;
}
