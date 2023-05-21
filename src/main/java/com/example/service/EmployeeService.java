package com.example.service;

import com.example.common.BaseResponse;
import com.example.model.dto.LoginEmployeeDTO;
import com.example.model.dto.RegisterEmployeeDTO;
import com.example.model.dto.UpdateStudentMessageDTO;
import com.example.model.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
* @author L
* @description 针对表【employee】的数据库操作Service
* @createDate 2023-05-21 19:40:06
*/
public interface EmployeeService extends IService<Employee> {
    BaseResponse register(@RequestParam RegisterEmployeeDTO registerEmployeeDTO);

    BaseResponse login(@RequestParam LoginEmployeeDTO loginEmployeeDTO);

    BaseResponse showMyMessage(HttpServletRequest httpServletRequest);
    /*
    迎新工作
     */

    BaseResponse updateStudentMessage(HttpServletRequest httpServletRequest,@RequestParam UpdateStudentMessageDTO updateStudentMessageDTO);
/*
查找某个学生的成绩
 */
    BaseResponse showStudentScore(HttpServletRequest httpServletRequest, Integer studentId);

}
