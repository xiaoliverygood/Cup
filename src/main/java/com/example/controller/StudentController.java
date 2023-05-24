package com.example.controller;

import com.example.common.BaseResponse;
import com.example.model.dto.FindPasswordDTO;
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

    /**
     * 学生注册
     */
    @PostMapping("/register")
    public BaseResponse register(@RequestBody RegisterStudentDTO registerStudentDTO) {
        return studentService.register(registerStudentDTO);
    }

    /**
     * 学生登录
     */
    @PostMapping("/login")
    public BaseResponse login(@RequestBody LoginStudentDTO loginStudentDTO) {
        return studentService.login(loginStudentDTO);
    }

    /**
     * 学生修改个人信息
     */

    @PutMapping("/updateInfo")
    public BaseResponse updateInfo(@RequestBody UpdateStudentDTO updateStudentDTO, HttpServletRequest httpServletRequest) {
        return studentService.updateInfo(updateStudentDTO, httpServletRequest);
    }

    /**
     * 学生查询个人信息
     */
    @GetMapping("/queryInfo")
    public BaseResponse queryInfo(HttpServletRequest httpServletRequest) {
        return studentService.queryInfo(httpServletRequest);
    }

    /**
     * 找回密码
     */
    @PutMapping("/findPassword")
    public BaseResponse findPassword(@RequestBody FindPasswordDTO findPasswordDTO) {
        return studentService.findPassword(findPasswordDTO);
    }

    /**
     * 退出登录
     */

    @PostMapping("/logout")
    public BaseResponse logout(HttpServletRequest httpServletRequest) {
        return studentService.logout(httpServletRequest);
    }

}
