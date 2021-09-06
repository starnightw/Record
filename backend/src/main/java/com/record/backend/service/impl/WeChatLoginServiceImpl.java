package com.record.backend.service.impl;

import com.record.backend.mapping.UserMapping;
import com.record.backend.mapping.WeChatUserMapping;
import com.record.backend.pojo.Result;
import com.record.backend.pojo.User;
import com.record.backend.pojo.WeChatUser;
import com.record.backend.service.WeChatLoginService;
import com.record.backend.utils.ComonUtils;
import com.record.backend.utils.JWTUtile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Along
 * @Date: Created in 10:11 2021/9/4
 **/
@Service
public class WeChatLoginServiceImpl implements WeChatLoginService {

    @Autowired
    private WeChatUserMapping weChatUserMapping;

    @Autowired
    private UserMapping userMapping;

    @Override
    public Result WeChatLogin(String code) {

        String url = ComonUtils.appendUrl(code).toString();
        RestTemplate restTemplate = new RestTemplate();
        //用restTemplate请求url，得到openid和session_key
        String repose = restTemplate.getForObject(url, String.class);
        System.out.println(repose);
        assert repose != null;
        String[] strings = repose.split(",");
        String openid = strings[1].substring(10, strings[1].length()-2);
        String session_key = strings[0].substring(16, strings[0].length()-1);
        System.out.println("openid==>"+openid+"==session_key=`  =>"+session_key);
        String token = JWTUtile.produceToken(openid,session_key);
        System.out.println("token===>"+token);
        WeChatUser weChatUser = weChatUserMapping.selectByOpenId(openid);
        System.out.println(weChatUser);
        if(weChatUser == null){
            assert false;
            WeChatUser weChatUserAdd = new WeChatUser();
            weChatUserAdd.setOpenid(openid);
            weChatUserAdd.setSession_key(session_key);
            weChatUserMapping.addWeChatUser(weChatUserAdd);
            Map<String,Object> map = new HashMap<>();
            map.put("token",token);
            map.put("openid",openid);
            System.out.println("登录成功,请尽快关联qluoj账号");
            return new Result(201,"登录成功,请尽快关联qluoj账号",map);
        }else {
            if(weChatUser.getUser_id() == null){
                Map<String,Object> map = new HashMap<>();
                map.put("token",token);
                map.put("openid",openid);
                System.out.println("还未关联qluoj账号");
                return new Result(202,"登录成功,还未关联qluoj账号,将影响正常使用",map);
            }else {
                System.out.println(weChatUser.getUser_id());
                User user = userMapping.selectByUserId(weChatUser.getUser_id());
                System.out.println(user.toString());
                Map<String,Object> map = new HashMap<>();
                map.put("token",token);
                map.put("openid",openid);
                map.put("user_id", String.valueOf(user.getId()));
                map.put("nickName",user.getNickname());
                System.out.println("登录成功");
                return new Result(200,"登录成功",map);
            }
        }
    }

    @Override
    public Boolean WeChatIfLogin(String openId, String token) {

        return JWTUtile.decodeToken(token,openId);
    }
}
