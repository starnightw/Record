package com.record.backend.controller;

import com.record.backend.pojo.Result;
import com.record.backend.service.ContestViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.CompletionService;

/**
 * @Author: Along
 * @Date: Created in 19:32 2021/9/4
 **/
@Controller
public class ContestViewController {

    @Autowired
    private ContestViewService contestViewService;

    @RequestMapping(value="/contest/getallbytype", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllContestByType() {
        return contestViewService.getAllContestViewByType();
    }

}
