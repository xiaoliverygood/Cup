package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginStudentDTO {
    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */

    private String pwd;
}
