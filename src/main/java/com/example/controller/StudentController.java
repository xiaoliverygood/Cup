package com.example.controller;

import com.example.common.BaseResponse;
import com.example.model.dto.LoginStudentDTO;
import com.example.model.dto.RegisterStudentDTO;
import com.example.model.dto.UpdateStudentDTO;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/Student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/register")
    public BaseResponse register(@RequestBody RegisterStudentDTO registerStudentDTO){
        return studentService.register(registerStudentDTO );
    }
    @PostMapping("/login")
    public BaseResponse login(@RequestBody LoginStudentDTO loginStudentDTO){
        return studentService.login(loginStudentDTO );
    }

    @PutMapping("/updateInfo")
    public BaseResponse updateInfo(@RequestBody UpdateStudentDTO updateStudentDTO, HttpServletRequest httpServletRequest){
        return studentService.updateInfo(updateStudentDTO,httpServletRequest);
    }

    @GetMapping("/queryInfo")
    public BaseResponse queryInfo(HttpServletRequest httpServletRequest){
        return studentService.queryInfo(httpServletRequest);
    }



}
