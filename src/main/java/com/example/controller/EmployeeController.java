package com.example.controller;

import com.example.common.BaseResponse;
import com.example.model.dto.*;
import com.example.service.CourseService;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    CourseService courseService;
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
    public BaseResponse findPassword(@RequestBody FindPasswordDTO findPasswordDTO){
        return employeeService.findPassword(findPasswordDTO);
    }
    /*
    查询我学生名单
     */
    @GetMapping("/findMyStudent")
    public BaseResponse findMyStudent(HttpServletRequest httpServletRequest){

        return employeeService.findMyStudent(httpServletRequest);
    }

    /*
    通过学生id导入课程（使用课程id）
     */
    @PostMapping("ImportCourses")
    public BaseResponse importCoursesByStudentId(HttpServletRequest httpServletRequest,Integer studentId,Integer courseId){
        return employeeService.importCoursesByStudentId(httpServletRequest,studentId,courseId);
    }

    /*
    查看所有课程
     */

    @GetMapping("/showAllCourses")
    public BaseResponse showAllCourses(){
        return BaseResponse.success(courseService.getCourseList());
    }
//

//    ----------------------宿舍管理员-----------------------------
    /*
    宿舍报修：修改宿舍表保修字段
     */
    @PutMapping("/repair")
    public BaseResponse repair(HttpServletRequest httpServletRequest,@RequestBody RepairSupervisorDTO repairEmployeeDTO){

        return employeeService.repair(httpServletRequest,repairEmployeeDTO);
    }

    /*
    违纪登记：宿舍表违纪字段
     */
    @PutMapping("/violation")
    public BaseResponse violation(HttpServletRequest httpServletRequest,@RequestBody ViolationSupervisorDTO violationEmployeeDTO){

        return employeeService.violation(httpServletRequest,violationEmployeeDTO);
    }

    /*
    查看学生住宿信息
     */
//
    @GetMapping("/getDormitory")
    public BaseResponse getDormitoryByStudentId(HttpServletRequest httpServletRequest,Integer studentId){

        return employeeService.getDormitoryByStudentId(httpServletRequest,studentId);

    }

    /*
    查看留校人员
     */
    @GetMapping("/getLeave")
    public BaseResponse getLeaveAllStudent(HttpServletRequest httpServletRequest){

        return employeeService.getLeaveAllStudent(httpServletRequest);

    }
//    ----------------------院系负责人----------------------------

    /*
    给辅导员评分
     */
    @PutMapping("/scoreCounselor")
    public BaseResponse scoreCounselor(HttpServletRequest httpServletRequest,Integer counselorId,Double score){

        return employeeService.scoreCounselor(httpServletRequest,counselorId,score);
    }

    /*
    添加科目
     */
    @PostMapping("/addCourse")
    public BaseResponse addCourse(HttpServletRequest httpServletRequest,String courseName){

        return employeeService.addCourse(httpServletRequest,courseName);
    }

    //----------------------学工管理器-----------------------------
    @GetMapping("/queryStudentById")
    public BaseResponse queryStudent(Integer id , HttpServletRequest httpServletRequest){
        return employeeService.queryStudentById(id);
    }

    @PutMapping("/updateStudent")
    public BaseResponse updateStudent(@RequestBody UpdateStudentByEmpDTO updateStudentByEmpDTO, HttpServletRequest httpServletRequest){
        return employeeService.updateStudent(updateStudentByEmpDTO,httpServletRequest);
    }

    @GetMapping("/queryStudentList")
    public BaseResponse queryStudentList(Integer pageNum, Integer pageSize){
        return employeeService.queryStudentList(pageNum,pageSize);
    }


    @PostMapping("/logout")
    public BaseResponse logout(HttpServletRequest httpServletRequest){
        return employeeService.logout(httpServletRequest);
    }

    @DeleteMapping("/deleteUser")
    public BaseResponse deleteUser(Integer id,HttpServletRequest httpServletRequest){
        return employeeService.deleteUser(id);
    }
}
