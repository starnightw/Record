package com.record.backend.config;

import com.google.gson.Gson;
import com.record.backend.pojo.Record;
import com.record.backend.pojo.Result;
import com.record.backend.pojo.User;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

/**
 * @Author: Along
 * @Date: Created in 10:34 2021/9/2
 **/
@Component
public class TokenHandlerInterceptor implements HandlerInterceptor {
    /**
     * 忽略拦截的url
     */
    private final String[] urls = {
            "/wechat/login"
    };

    /**
     * 进入controller层之前拦截请求
     * @param request
     * @param response
     * @param handler
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录成功之后，应该有用户的token，
        String url = request.getRequestURI();
        String token = request.getHeader("token");
        String method = request.getMethod();
        if (method.equals("POST")){
            System.out.println("request请求地址path["+request.getServletPath()+"] uri["+url+"]");
            System.out.println("request请求的token["+token+"]");
            System.out.println("request请求的method["+method+"]");
            // 遍历需要忽略拦截的路径
            for (String item : this.urls){
                System.out.println(item+"为忽略拦截的路径=》通过");
                if (item.equals(url)){
                    return true;
                }
            }
            // 查询验证token
            // User userByToken = userService.getUserByToken(token);
            // if (userByToken == null){
            //     httpServletResponse.setCharacterEncoding("UTF-8");
            //     httpServletResponse.setContentType("application/json; charset=utf-8");
            //     PrintWriter out = null ;
            //     try{
            //         Result res = new Result(10001,"登录失效重新登录");
            //         String json = JSON.toJSONString(res);
            //         request.setContentType("application/json");
            //         out = request.getWriter();
            //         // 返回json信息给前端
            //         out.append(json);
            //         out.flush();
            //         return false;
            //     } catch (Exception e){
            //         e.printStackTrace();
            //         request.sendError(500);
            //         return false;
            //     }
            // }
            return true;
        }
        return false;
    }   




    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
