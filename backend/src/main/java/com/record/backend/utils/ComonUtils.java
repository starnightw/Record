package com.record.backend.utils;

/**
 * @Author: Along
 * @Date: Created in 11:02 2021/9/4
 * 封装微信小程序appid, secret js_code  为获得用户openId
 **/
public class ComonUtils {

    public static StringBuffer appendUrl(String code) {

        StringBuffer info = new StringBuffer("https://api.weixin.qq.com/sns/jscode2session?");

        info.append("appid=").append("wxd722670c64cee6ae").append("&");

        info.append("secret=").append("e38de895ddf65852e26c8510b08998ec").append("&");

        info.append("js_code=").append(code).append("&");

        info.append("grant_type=").append("authorization_code");

        return info;
    }

}
