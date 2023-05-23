package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindPasswordDTO {
    /**
    邮箱
     */
    private String email;
    /**
    新密码
     */
    private String newPassword;
    /**
    邮箱验证码
     */
    private String code;
}
