package com.record.backend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Objects;

/**
 * @Author: Along
 * @Date: Created in 15:59 2021/9/4
 **/
public class JWTUtile {

    public static String produceToken(String openid,String session_key) {

        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis()+ 60L * 1000L * 120L))  //设置过期时间 120分钟
                .withAudience(openid) //设置接受方信息，一般时登录用户
                .sign(Algorithm.HMAC256(session_key));
    }

    public static boolean decodeToken(String token, String openid) {
        return Objects.equals(openid, JWT.decode(token).getAudience().get(0));
    }
}
