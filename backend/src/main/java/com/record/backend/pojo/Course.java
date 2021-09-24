package com.record.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Along
 * @Date: Created in 0:11 2021/9/25
 *     id : int
 *     name : String,  // 点击的导航按钮
 *     title : String, // 视频的title
 *     videoLink : String,  // 视频的bilibili链接
 *     recordContent : String,  // markdown内容  作为展示记录
 *     downPdfLink : String,  // 课程PDF的下载url
 *     downVideoLink : String  // 课程视频的下载url
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private Integer id;
    private Integer type_id;
    private String name; // 点击的导航按钮
    private String title; // 视频的title
    private String videoLink; // 视频的bilibili链接
    private String recordContent; // markdown内容  作为展示记录
    private String downPdfLink; // 课程PDF的下载url
    private String downVideoLink; // 课程视频的下载url
}
