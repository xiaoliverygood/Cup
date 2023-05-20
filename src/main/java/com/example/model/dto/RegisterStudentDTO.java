package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterStudentDTO {
    private String name;

    /**
     * 学生年龄
     */
    private Integer age;

    /**
     *性别
     */
    private String sex;

    /**
     *邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 手机号
     *
     */
    private String phone;

    /**
     * 验证码
     */
    private String code;
}
