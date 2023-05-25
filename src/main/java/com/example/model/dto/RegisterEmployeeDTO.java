package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterEmployeeDTO {
    private String pwd;

    /**
     *密码
     */
    private String email;

    /**
     * 邮箱
     *
     */
    private String name;

    /**
     *姓名
     */
    private String phone;

    /**
     *手机号
     */
    private String sex;

    /**
     *性别
     */
    private String authority;
    /**
     *权限（注册为管理员还是什么）
     */

    private String code;


}
