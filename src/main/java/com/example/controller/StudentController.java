package com.example.controller;

import com.example.common.BaseResponse;
import com.example.model.dto.RegisterStudentDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Student")
public class StudentController {
    @PostMapping("/register")
    public BaseResponse register(@RequestBody RegisterStudentDTO registerStudentDTO){

    }


}
