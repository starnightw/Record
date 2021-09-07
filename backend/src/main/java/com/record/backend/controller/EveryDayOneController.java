package com.record.backend.controller;

import com.record.backend.pojo.Result;
import com.record.backend.service.ContestViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Along
 * @Date: Created in 0:10 2021/9/7
 **/
@Controller
public class EveryDayOneController {

    @Autowired
    private ContestViewService contestViewService;

    @RequestMapping(value = "/everyDay/teaching",method = RequestMethod.POST)
    @ResponseBody
    public Result everyDayTeaching() {
        return contestViewService.getAllEveryDayTeach();
    }
}
