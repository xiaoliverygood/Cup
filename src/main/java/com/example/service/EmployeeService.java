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

    BaseResponse findPassword(@RequestBody FindPasswordDTO findPasswordDTO);

    /*
    查询我的学生
     */
    BaseResponse findMyStudent(HttpServletRequest httpServletRequest);


//    ---------------------宿舍管理员------------------------\

    /**
     * 根据学生id和宿舍id添加到宿舍联系表
     * @param httpServletRequest
     * @param studentId
     * @param dormitoryId
     * @return
     */
    BaseResponse addStudentDormitory(HttpServletRequest httpServletRequest,Integer studentId,Integer dormitoryId);

    /**
     * 根据学生id实现宿舍删除学生
     * @param httpServletRequest
     * @param studentId
     * @return
     */

    BaseResponse deleteStudentByDormitory(HttpServletRequest httpServletRequest,Integer studentId);

    /**
     * 创建宿舍

     */
    BaseResponse createDormitory(HttpServletRequest httpServletRequest,@RequestBody CreateDormitoryDTO createDormitoryDTO);

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

    /*
    给辅导员评分
     */
    BaseResponse scoreCounselor(HttpServletRequest httpServletRequest,Integer counselorId,Double score);


    /*
    院系负责人添加课程
     */
    BaseResponse addCourse(HttpServletRequest httpServletRequest,String courseName);
    BaseResponse queryStudentById(Integer id);

    BaseResponse updateStudent(UpdateStudentByEmpDTO updateStudentByEmpDTO, HttpServletRequest httpServletRequest);

    BaseResponse queryStudentList(Integer pageNum, Integer pageSize);
    /*
    退出登录
     */

    BaseResponse logout(HttpServletRequest httpServletRequest);

    /*
    通过学生id，课程id进行建立联系，也就是学生添加课程
     */

    BaseResponse importCoursesByStudentId(HttpServletRequest httpServletRequest,Integer studentId,Integer courseId);

    BaseResponse deleteUser(Integer id);

    BaseResponse updateLinkStuEmp(UpdateLinkStuEmpDTO updateLinkStuEmpDTO,HttpServletRequest httpServletRequest);

    BaseResponse addLinkStuEmp(Integer sId, Integer eId, HttpServletRequest httpServletRequest);
}
