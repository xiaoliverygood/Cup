package com.example.controller;

import com.example.common.BaseResponse;
import com.example.model.dto.*;
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
    public BaseResponse register(@RequestBody RegisterEmployeeDTO registerEmployeeDTO){
        return employeeService.register(registerEmployeeDTO);
    }
    @PostMapping("/login")
    public BaseResponse login(@RequestBody LoginEmployeeDTO loginEmployeeDTO){
        return employeeService.login(loginEmployeeDTO);
    }
    @GetMapping("/showMyMessage")
    public BaseResponse showMyMessage(HttpServletRequest httpServletRequest){
        return employeeService.showMyMessage(httpServletRequest);
    }
//    ----------------------------辅导员的功能---------------------------------
    //修改学生档案信息
    @PutMapping("/updateStudentMessage")
    public BaseResponse updateStudentMessage(HttpServletRequest httpServletRequest,@RequestBody UpdateStudentMessageDTO updateStudentMessageDTO){
        return employeeService.updateStudentMessage(httpServletRequest,updateStudentMessageDTO);
    }
    //查询某学生成绩

    @GetMapping("/showStudentScore")
    public BaseResponse showStudentScore(HttpServletRequest httpServletRequest, Integer studentId){
        return employeeService.showStudentScore(httpServletRequest,studentId);
    }
    /*
    找回密码
     */

    @PutMapping("/findPassword")
    public BaseResponse findPassword(@RequestBody FindPasswordEmployeeDTO findPasswordDTO){
        return employeeService.findPassword(findPasswordDTO);
    }
    /*
    查询我学生名单
     */
    @GetMapping("/findMyStudent")
    public BaseResponse findMyStudent(HttpServletRequest httpServletRequest){
        return employeeService.findMyStudent(httpServletRequest);
    }


//    ----------------------宿舍管理员-----------------------------
    /*
    宿舍报修：修改宿舍表保修字段
     */
    @PutMapping("/repair")
    public BaseResponse repair(HttpServletRequest httpServletRequest,@RequestBody RepairSupervisorDTO repairEmployeeDTO){
        return employeeService.repair(httpServletRequest,repairEmployeeDTO);
    }



}
