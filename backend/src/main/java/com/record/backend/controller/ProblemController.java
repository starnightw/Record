package com.record.backend.controller;

import com.record.backend.pojo.Result;
import com.record.backend.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Author: Along
 * @Date: Created in 19:32 2021/9/4
 **/
@Controller
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @RequestMapping(value="/problem/getalltags", method = RequestMethod.POST)
    @ResponseBody
    public Result problemsTags() {

        return problemService.problemsTags();
    }

    @RequestMapping(value="/problem/getproblemsbytags", method = RequestMethod.POST)
    @ResponseBody
    public Result problemsByTags(@RequestBody Map<String,String> reqbody) {
        String tags = reqbody.get("tags");
        return problemService.problemsByTags(tags);
    }

    @RequestMapping(value="/problem/getproblembyid", method = RequestMethod.POST)
    @ResponseBody
    public Result problemById(@RequestBody Map<String,String> reqbody) {
        Integer id = Integer.valueOf(reqbody.get("id"));
        return problemService.problemById(id);
    }

}
