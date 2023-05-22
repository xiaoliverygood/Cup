package com.example.service;

import com.example.common.BaseResponse;
import com.example.model.dto.*;
import com.example.model.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;
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

//    ------------辅导员------------
    /*
    迎新工作
     */

    BaseResponse updateStudentMessage(HttpServletRequest httpServletRequest,@RequestParam UpdateStudentMessageDTO updateStudentMessageDTO);
/*
查找某个学生的成绩
 */
    BaseResponse showStudentScore(HttpServletRequest httpServletRequest, Integer studentId);
    /*
    找回密码
     */

    BaseResponse findPassword(@RequestBody FindPasswordEmployeeDTO findPasswordDTO);

    /*
    查询我的学生
     */
    BaseResponse findMyStudent(HttpServletRequest httpServletRequest);


//    ---------------------宿舍管理员------------------------

    /*
    宿舍管理员：报修宿舍
     */

    BaseResponse repair(HttpServletRequest httpServletRequest,@RequestBody RepairSupervisorDTO repairEmployeeDTO);

    /*
    违纪情况登记
     */
    BaseResponse violation(HttpServletRequest httpServletRequest,@RequestBody ViolationSupervisorDTO violationEmployeeDTO);

    /*
    根据学生id查询宿舍
     */

    BaseResponse getDormitoryByStudentId(HttpServletRequest httpServletRequest,Integer studentId);

    /*
    查看学生留校的名单
     */

    BaseResponse getLeaveAllStudent(HttpServletRequest httpServletRequest);

}
