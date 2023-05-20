package com.example.interceptor;


import com.example.utils.JudgmentToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    JudgmentToken judgmentToken;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(judgmentToken.Judgment(request)){
            return true;
        }
        if(request.getHeader("token")==null){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"message\":\"用户未登录\"}");
        }
        if(request.getHeader("token")!=null){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"message\":\"用户token已经过期\"}");
        }
        return false;
    }
}
