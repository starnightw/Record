package com.record.backend.service;

import com.record.backend.pojo.Result;

/**
 * @Author: Along
 * @Date: Created in 19:22 2021/9/5
 **/
public interface ProblemService {

    Result problemsTags();

    Result problemsByTags(String tags);

    Result problemById(Integer id);

}
