package com.example.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginEmployeeVo {
    private String email;

    private String name;

    private String phone;

    private String sex;

    private String authority;

    private String token;

}
