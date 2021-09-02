package com.record.backend.controller;

import com.google.gson.Gson;
import com.record.backend.pojo.Result;
import com.record.backend.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Along
 * @Date: Created in 9:15 2021/9/2
 **/
@Controller
public class WeChatLoginController {
    @RequestMapping(value="/wechat/login", method = RequestMethod.POST)
    @ResponseBody
    public Result wxLogin(@RequestBody Map<String,String> reqbody){
        System.out.println(reqbody.get("code"));
        Map<String,Object> userMap = new HashMap<>();
        List<Object> userList = new ArrayList<>();
        userList.add(new User(1,"ssss","ssss","ssss"));
        userList.add(new User(1,"ssss","ssss","ssss"));
        userMap.put("user",userList);
        return new Result(200,"登录失效重新登录",userMap);
    }
}
