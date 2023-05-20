package com.example.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
@Component
public class JudgmentToken {
    @Autowired
    StringRedisTemplate template;//连接redis，并注册为bean
    public  Boolean Judgment(HttpServletRequest httpServletRequest){
        if(httpServletRequest.getHeader("token")!=null&&template.hasKey(httpServletRequest.getHeader("token"))){
            return true;
        }else {
            return false;
        }
    }
}
