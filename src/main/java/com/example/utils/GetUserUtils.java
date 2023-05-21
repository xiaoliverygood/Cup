package com.example.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class GetUserUtils {
    @Autowired
    StringRedisTemplate template;

    public  Integer getId(HttpServletRequest httpServletRequest){
        return Integer.valueOf(template.opsForValue().get(httpServletRequest.getHeader("token")));
    }



}
