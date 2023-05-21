package com.example.controller;

import com.example.common.BaseResponse;
import com.example.model.dto.LoginEmployeeDTO;
import com.example.model.dto.RegisterEmployeeDTO;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping("/register")
    public BaseResponse register(@RequestParam RegisterEmployeeDTO registerEmployeeDTO){
        return employeeService.register(registerEmployeeDTO);
    }
    @PostMapping("/login")
    public BaseResponse login(@RequestParam LoginEmployeeDTO loginEmployeeDTO){
        return employeeService.login(loginEmployeeDTO);
    }
}
