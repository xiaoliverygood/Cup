package com.example.controller;

import com.example.common.BaseResponse;
import com.example.model.dto.LoginEmployeeDTO;
import com.example.model.dto.RegisterEmployeeDTO;
import com.example.model.dto.UpdateStudentMessageDTO;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    @GetMapping("/showMyMessage")
    public BaseResponse showMyMessage(HttpServletRequest httpServletRequest){
        return employeeService.showMyMessage(httpServletRequest);
    }
//    ----------------------------辅导员的功能---------------------------------
    @PutMapping("/updateStudentMessage")
    public BaseResponse updateStudentMessage(@RequestParam UpdateStudentMessageDTO updateStudentMessageDTO){
        return employeeService.updateStudentMessage(updateStudentMessageDTO);
    }


}
