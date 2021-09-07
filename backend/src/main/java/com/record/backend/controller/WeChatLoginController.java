package com.record.backend.controller;

import com.google.gson.Gson;
import com.record.backend.pojo.Result;
import com.record.backend.pojo.User;
import com.record.backend.service.WeChatLoginService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private WeChatLoginService weChatLoginService;

    @RequestMapping(value="/wechat/login", method = RequestMethod.POST)
    @ResponseBody
    public Result wxLogin(@RequestBody Map<String,String> reqbody){
        String code = reqbody.get("code");
        System.out.println("code==>"+code);
        return weChatLoginService.WeChatLogin(code);
    }

    @RequestMapping(value="/wechat/updateuserid", method = RequestMethod.POST)
    @ResponseBody
    public Result wxUpdateUserId(@RequestBody Map<String,String> reqbody){
        String openid = reqbody.get("openid");
        String username = reqbody.get("username");
        System.out.println(username+"=====");
        return weChatLoginService.WechatLoginAddUserId(openid,username);
    }

    // @RequestMapping(value="/wechat/iflogin", method = RequestMethod.POST)
    // @ResponseBody
    // public Result wxIfLogin(@RequestBody Map<String,String> reqbody){
    //     String code = reqbody.get("code");
    //     System.out.println("code==>"+code);
    //     Map<String,String> map = new HashMap<>();
    //     map.put("iflogin", String.valueOf(iflogin));
    //     return new Result(200,"登录成功",map);
    // }
}
