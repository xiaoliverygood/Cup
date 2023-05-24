package com.example.controller;

import com.example.common.BaseResponse;

import com.example.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaptchaController {
    @Autowired
    CaptchaUtil captchaUtil;

    /**
     * 发送验证码的接口
     */
    @GetMapping("/captcha")
    public BaseResponse<String> getCaptcha(String email) {
        return BaseResponse.success(captchaUtil.RigisterCode(email));
    }

    /**
     * 找回密码的验证码发送接口
     */
    @GetMapping("/findPasswordCaptcha")
    public BaseResponse<String> findpasswordCaptcha(String emailAddress) {
        return BaseResponse.success(captchaUtil.findPasswordCode(emailAddress));
    }

}
