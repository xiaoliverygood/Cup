package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginEmployeeDTO {
    /*
    邮箱
     */
    private String email;
    /*
    密码
     */

    private String pwd;
}
