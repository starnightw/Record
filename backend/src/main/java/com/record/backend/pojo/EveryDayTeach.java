package com.record.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Along
 * @Date: Created in 1:02 2021/9/7
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EveryDayTeach {
    private Integer id;
    private String name;
    private String title;
    private String recordContent;
}
