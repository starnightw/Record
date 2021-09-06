package com.record.backend.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: Along
 * @Date: Created in 8:46 2021/9/3
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContestView {
    private String cid;
    private String title;
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Long begin_time;
    private Long end_time;
    private String duration; //持续时间
    private String url; // 比赛url链接
    private Integer type; // 来源 分类

    // public ContestView(String cid,String title,Long begin_time,Long end_time,String duration,String url,Integer type){
    //     this.cid = cid;
    //     this.title = title;
    //     //这个是你要转成后的时间的格式
    //     SimpleDateFormat sdff=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //     this.begin_time =sdff.format(new Date(begin_time));
    //
    //     this.end_time = end_time;
    //     this.duration = duration;
    //     this.url = url;
    //     this.type = type;
    //
    // }


}
