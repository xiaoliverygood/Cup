package com.example.controller;

import com.example.common.BaseResponse;
import com.example.model.dto.LoginStudentDTO;
import com.example.model.dto.RegisterStudentDTO;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/register")
    public BaseResponse register(@RequestBody RegisterStudentDTO registerStudentDTO){

        return studentService.register(registerStudentDTO);
    }
    @PostMapping("/login")
    public BaseResponse login(@RequestBody LoginStudentDTO loginStudentDTO){
        return studentService.login(loginStudentDTO);
    }


}
