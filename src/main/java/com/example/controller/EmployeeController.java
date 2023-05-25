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

    /**
     * 辅导员教职工注册
     *
     * @param registerEmployeeDTO
     * @return
     */
    @PostMapping("/register")
    public BaseResponse register(@RequestBody RegisterEmployeeDTO registerEmployeeDTO) {
        return employeeService.register(registerEmployeeDTO);
    }

    /**
     * 教职工登录
     *
     * @param loginEmployeeDTO
     * @return
     */
    @PostMapping("/login")
    public BaseResponse login(@RequestBody LoginEmployeeDTO loginEmployeeDTO) {
        return employeeService.login(loginEmployeeDTO);
    }

    /**
     * 展示我的个人信息（教职工）
     *
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/showMyMessage")
    public BaseResponse showMyMessage(HttpServletRequest httpServletRequest) {
        return employeeService.showMyMessage(httpServletRequest);
    }
//    ----------------------------辅导员的功能---------------------------------
    //修改学生档案信息

    /**
     * 修改学生信息，迎新工作辅导员的功能
     *
     * @param httpServletRequest
     * @param updateStudentMessageDTO
     * @return
     */
    @PutMapping("/updateStudentMessage")
    public BaseResponse updateStudentMessage(HttpServletRequest httpServletRequest, @RequestBody UpdateStudentMessageDTO updateStudentMessageDTO) {
        return employeeService.updateStudentMessage(httpServletRequest, updateStudentMessageDTO);
    }
    //查询某学生成绩

    /**
     * 查询某学生的成绩
     *
     * @param httpServletRequest
     * @param studentId
     * @return
     */

    @GetMapping("/showStudentScore")
    public BaseResponse showStudentScore(HttpServletRequest httpServletRequest, Integer studentId) {
        return employeeService.showStudentScore(httpServletRequest, studentId);
    }
    /*
    找回密码
     */

    /**
     * 找回密码(教职工忘记密码)
     *
     * @param findPasswordDTO
     * @return
     */

    @PutMapping("/findPassword")
    public BaseResponse findPassword(@RequestBody FindPasswordDTO findPasswordDTO) {
        return employeeService.findPassword(findPasswordDTO);
    }

    /**
     * 查询我的学生（我所带的学生花名册）
     */
    @GetMapping("/findMyStudent")
    public BaseResponse findMyStudent(HttpServletRequest httpServletRequest) {

        return employeeService.findMyStudent(httpServletRequest);
    }

    /**
     * 通过学生id导入课程（使用课程id）
     */
    @PostMapping("ImportCourses")
    public BaseResponse importCoursesByStudentId(HttpServletRequest httpServletRequest, Integer studentId, Integer courseId) {
        return employeeService.importCoursesByStudentId(httpServletRequest, studentId, courseId);
    }

    /**
     * 查看所有课程
     */

    @GetMapping("/showAllCourses")
    public BaseResponse showAllCourses() {
        return BaseResponse.success(courseService.getCourseList());
    }
//

//    ----------------------宿舍管理员-----------------------------

    /**
     * 宿舍添加学生（宿舍管理员）
     *
     * @param httpServletRequest
     * @param studentId
     * @param dormitoryId
     * @return
     */
    @PostMapping("/AddStudentDormitory")
    public BaseResponse addStudentDormitory(HttpServletRequest httpServletRequest, Integer studentId, Integer dormitoryId) {
        return employeeService.addStudentDormitory(httpServletRequest, studentId, dormitoryId);
    }

    /**
     * 宿舍删除学生（根据宿舍id和学生id）（宿舍管理员）
     */
    @DeleteMapping("/deleteStudentByDormitory")
    public BaseResponse deleteStudentByDormitory(HttpServletRequest httpServletRequest, Integer studentId) {
        return employeeService.deleteStudentByDormitory(httpServletRequest, studentId);
    }

    /**
     * 创建宿舍，也就是新增宿舍（宿舍管理员）
     */
    @PostMapping("/createDormitory")
    public BaseResponse createDormitory(HttpServletRequest httpServletRequest, @RequestBody CreateDormitoryDTO createDormitoryDTO) {

        return employeeService.createDormitory(httpServletRequest, createDormitoryDTO);
    }

    /**
     * 宿舍报修：修改宿舍表保修字段（宿舍管理员）
     */
    @PutMapping("/repair")
    public BaseResponse repair(HttpServletRequest httpServletRequest, @RequestBody RepairSupervisorDTO repairEmployeeDTO) {

        return employeeService.repair(httpServletRequest, repairEmployeeDTO);
    }

    /**
     * 违纪登记：宿舍表违纪字段（宿舍管理员）
     */
    @PutMapping("/violation")
    public BaseResponse violation(HttpServletRequest httpServletRequest, @RequestBody ViolationSupervisorDTO violationEmployeeDTO) {

        return employeeService.violation(httpServletRequest, violationEmployeeDTO);
    }

    /**
     * 查看学生住宿信息（宿舍管理员）
     */
//
    @GetMapping("/getDormitory")
    public BaseResponse getDormitoryByStudentId(HttpServletRequest httpServletRequest, Integer studentId) {

        return employeeService.getDormitoryByStudentId(httpServletRequest, studentId);

    }

    /**
     * 查看留校人员（宿舍管理员）
     */
    @GetMapping("/getLeave")
    public BaseResponse getLeaveAllStudent(HttpServletRequest httpServletRequest) {

        return employeeService.getLeaveAllStudent(httpServletRequest);

    }
//    ----------------------院系负责人----------------------------

    /**
     * 给辅导员评分（院系负责人）
     */
    @PutMapping("/scoreCounselor")
    public BaseResponse scoreCounselor(HttpServletRequest httpServletRequest, Integer counselorId, Double score) {

        return employeeService.scoreCounselor(httpServletRequest, counselorId, score);
    }

    /**
     * 添加科目（院系负责人）
     */
    @PostMapping("/addCourse")
    public BaseResponse addCourse(HttpServletRequest httpServletRequest, String courseName) {

        return employeeService.addCourse(httpServletRequest, courseName);
    }

    //----------------------学工管理器-----------------------------

    /**
     * 学生处管理：通过学生id查询学生
     *
     * @param id
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/queryStudentById")
    public BaseResponse queryStudent(Integer id, HttpServletRequest httpServletRequest) {
        return employeeService.queryStudentById(id);
    }

    /**
     * 学生处管理：通过学生id修改学生信息（id必选，要改什么传什么）
     *
     * updateStudentByEmpDTO
     * @param httpServletRequest
     * @return
     */

    @PutMapping("/updateLinkStuEmp")
    public BaseResponse updateLinkStuEmp(@RequestBody UpdateLinkStuEmpDTO updateLinkStuEmpDTO,HttpServletRequest httpServletRequest){
        return employeeService.updateLinkStuEmp(updateLinkStuEmpDTO,httpServletRequest);
    }

    @PostMapping("/addLinkStuEmp")
    public BaseResponse addLinkStuEmp(Integer sId,Integer eId,HttpServletRequest httpServletRequest){
        return  employeeService.addLinkStuEmp(sId,eId,httpServletRequest);
    }




    @PutMapping("/updateStudent")
    public BaseResponse updateStudent(@RequestBody UpdateStudentByEmpDTO updateStudentByEmpDTO, HttpServletRequest httpServletRequest) {
        return employeeService.updateStudent(updateStudentByEmpDTO, httpServletRequest);
    }

    /**
     * 学生处管理：查询学生，分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */

    @GetMapping("/queryStudentList")
    public BaseResponse queryStudentList(Integer pageNum, Integer pageSize) {
        return employeeService.queryStudentList(pageNum, pageSize);
    }

    /**
     * 退出登录（教职工）
     *
     * @param httpServletRequest
     * @return
     */


    @PostMapping("/logout")
    public BaseResponse logout(HttpServletRequest httpServletRequest) {
        return employeeService.logout(httpServletRequest);
    }

    /**
     * 删除教职工或者学生，根据id号不同(学生处管理员权限)
     *
     * @param id
     * @param httpServletRequest
     * @return
     */

    @DeleteMapping("/deleteUser")
    public BaseResponse deleteUser(Integer id, HttpServletRequest httpServletRequest) {
        return employeeService.deleteUser(id);
    }
}
