package com.record.backend.controller;

import com.record.backend.pojo.Result;
import com.record.backend.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Author: Along
 * @Date: Created in 20:38 2021/9/6
 **/
@Controller
public class SolutionController {

    @Autowired
    private SolutionService solutionService;

    @RequestMapping(value="/solution/getallten", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllTen(@RequestBody Map<String,String> reqbody) {
        Integer user_id = Integer.valueOf(reqbody.get("userid"));
        return solutionService.selectByUserId(user_id);
    }
}
