package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.AppHttpCodeEnum;
import com.example.common.BaseResponse;
import com.example.mapper.DormitoryMapper;
import com.example.mapper.StudentMapper;
import com.example.model.dto.*;
import com.example.model.entity.*;
import com.example.model.vo.LoginEmployeeVo;
import com.example.model.vo.StudentCardVo;
import com.example.service.EmployeeService;
import com.example.mapper.EmployeeMapper;
import com.example.service.LinkStuEmpService;
import com.example.service.StudentService;
import com.example.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author L
 * @description 针对表【employee】的数据库操作Service实现
 * @createDate 2023-05-21 19:40:06
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
    @Autowired
    StringRedisTemplate template;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    StudentService studentService;
    @Autowired
    GetUserUtils getUserUtils;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    DormitoryMapper dormitoryMapper;
    @Autowired
    LinkStuEmpService linkStuEmpService;
    @Autowired
    JudgementStudentMany judgementStudentMany;

    @Override
    public BaseResponse register(RegisterEmployeeDTO registerEmployeeDTO) {
        String correctionCode = CaptchaUtil.EmailAndCode.get(registerEmployeeDTO.getEmail());

        boolean a = registerEmployeeDTO.getCode().equals(correctionCode);

        if (EmailRegularExpression.RegularEmailPattern(registerEmployeeDTO.getEmail()) && a) {

            Employee employee = BeanCopyUtils.copyBean(registerEmployeeDTO, Employee.class);

            this.save(employee);

            return BaseResponse.success("注册成功，请前往登录！");
        }
        return BaseResponse.Error(AppHttpCodeEnum.REQUIRE_USERNAME, registerEmployeeDTO);
    }

    @Override
    public BaseResponse login(LoginEmployeeDTO loginEmployeeDTO) {
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getEmail, loginEmployeeDTO.getEmail());


        boolean a = loginEmployeeDTO.getPwd().equals(this.getOne(queryWrapper).getPwd());
        if (EmailRegularExpression.RegularEmailPattern(loginEmployeeDTO.getEmail()) && a) {

            String token = UUID.randomUUID().toString();

            Employee employee = employeeMapper.findEmployeeByEmail(loginEmployeeDTO.getEmail());


            template.opsForValue().set(token, employee.getId().toString());
            //

            LoginEmployeeVo loginEmployeeVo = new LoginEmployeeVo(employee.getEmail(), employee.getName(), employee.getPhone(), employee.getSex(), employee.getAuthority(), token);

            return BaseResponse.success(loginEmployeeVo);//登录成功后返回Vo对象，里面带token


        } else {
            return BaseResponse.Error(AppHttpCodeEnum.LOGIN_ERROR, loginEmployeeDTO);
        }
    }

    @Override
    public BaseResponse showMyMessage(HttpServletRequest httpServletRequest) {

        Employee employee = employeeMapper.selectById(template.opsForValue().get(httpServletRequest.getHeader("token")));

        return BaseResponse.success(employee);
    }

    @Override
    public BaseResponse updateStudentMessage(HttpServletRequest httpServletRequest, UpdateStudentMessageDTO updateStudentMessageDTO) {
        Student student = studentService.getById(updateStudentMessageDTO.getStudentId());

        student.setInSchool(1);

        student.setMyClass(updateStudentMessageDTO.getClassName());

        student.setOrigin(updateStudentMessageDTO.getOrigin());

        student.setCheckIn(1);

        student.setSpeciality(updateStudentMessageDTO.getSpeciality());

        studentService.updateById(student);//迎新更新工作

        return BaseResponse.success(student);
    }

    //查询所有科目以及成绩
    @Override
    public BaseResponse showStudentScore(HttpServletRequest httpServletRequest, Integer studentId) {

        List<CourseBeUseSeearch> courseBeUseSeearchScoreList = employeeMapper.getCourseScoreList(studentId);

        return BaseResponse.success(courseBeUseSeearchScoreList);
    }

    /*
    老师通过学生id进行课程绑定
     */

    @Override
    public BaseResponse importCoursesByStudentId(HttpServletRequest httpServletRequest, Integer studentId, Integer courseId) {

        employeeMapper.insertStudentLinkCourse(courseId, studentId);

        return BaseResponse.success("添加成功");
    }

    @Override
    public BaseResponse deleteUser(Integer id) {
        if (id >= 2000) {
            employeeMapper.deleteById(id);
        } else
            studentMapper.deleteById(id);

        return BaseResponse.success("删除成功");
    }

    @Override
    public BaseResponse updateLinkStuEmp(UpdateLinkStuEmpDTO updateLinkStuEmpDTO,HttpServletRequest httpServletRequest) {
        /*
         *   学生id
         *   sId+eId+afterEid:为学生修改对应的职工
         *   sId+eId+afterSid:为职工修改对应的学生
         */

        LambdaQueryWrapper<LinkStuEmp> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(String.valueOf(updateLinkStuEmpDTO.getAfterEid()))){
            //传来sId,eId,AfterEid:根据学生id找记录
            queryWrapper.eq(LinkStuEmp::getStuId, updateLinkStuEmpDTO.getSId());
            //找到对应数据,为该对象修改对应的信息
            LinkStuEmp linkStuEmp = linkStuEmpService.getOne(queryWrapper);
            linkStuEmp.setEmpId(updateLinkStuEmpDTO.getAfterEid());
        }else if (StringUtils.hasText(String.valueOf(updateLinkStuEmpDTO.getAfterSid()))){
            //传来Eid,AfterSid:根据职工id找数据
            queryWrapper.eq(LinkStuEmp::getEmpId,updateLinkStuEmpDTO.getEId());
            LinkStuEmp linkStuEmp = linkStuEmpService.getOne(queryWrapper);
            linkStuEmp.setEmpId(updateLinkStuEmpDTO.getAfterSid());
        }
        return BaseResponse.success("修改成功");
    }

    @Override
    public BaseResponse addLinkStuEmp(Integer sId, Integer eId, HttpServletRequest httpServletRequest) {
        linkStuEmpService.save(new LinkStuEmp(sId,eId));

        return BaseResponse.success("增加成功");
    }




    /*
    院系负责人添加课程
     */

    @Override
    public BaseResponse addCourse(HttpServletRequest httpServletRequest, String courseName) {

        employeeMapper.addCourse(courseName);

        return BaseResponse.success("添加成功");
    }

    @Override
    public BaseResponse findPassword(FindPasswordDTO findPasswordDTO) {
        String correctPassword = CaptchaUtil.EmailAndCodeFindpassword.get(findPasswordDTO.getEmail());

        if (correctPassword.equals(findPasswordDTO.getCode())) {

            Employee employee = employeeMapper.findEmployeeByEmail(findPasswordDTO.getEmail());

            employee.setPwd(findPasswordDTO.getNewPassword());

            this.updateById(employee);

            CaptchaUtil.EmailAndCodeFindpassword.remove(findPasswordDTO.getEmail());

            return BaseResponse.success(employee);

        } else {

            return BaseResponse.Error(AppHttpCodeEnum.NO_OPERATOR_AUTH, findPasswordDTO);
        }
    }

    /*
    查询我的学生
     */

    @Override
    public BaseResponse findMyStudent(HttpServletRequest httpServletRequest) {
        Employee employee = this.getById(getUserUtils.getId(httpServletRequest));

        List<Student> studentList = employeeMapper.findStudentByTeacherId(employee.getId());//通过我的id查询我的学生

        return BaseResponse.success(studentList);
    }

//    -------宿舍管理员---------------

    /**
     * 宿舍添加学生信息，也就是宿舍学生联系表
     *
     * @param httpServletRequest
     * @param studentId
     * @param dormitoryId
     * @return
     */

    @Override
    public BaseResponse addStudentDormitory(HttpServletRequest httpServletRequest, Integer studentId, Integer dormitoryId) {

        if (judgementStudentMany.judgeStudentMany(dormitoryId)) {

            employeeMapper.insertStudentLinkDorm(studentId, dormitoryId);

            return BaseResponse.success("添加成功");

        } else {

            return BaseResponse.Error(AppHttpCodeEnum.SYSTEM_ERROR, "宿舍满人了");
        }
    }

    /**
     * 根据学生id删除学生信息
     *
     * @param httpServletRequest
     * @param studentId
     * @return
     */

    @Override
    public BaseResponse deleteStudentByDormitory(HttpServletRequest httpServletRequest, Integer studentId) {

        employeeMapper.deleteStudentLinkDorm(studentId);

        return BaseResponse.success("删除成功");

    }

    @Override
    public BaseResponse createDormitory(HttpServletRequest httpServletRequest, CreateDormitoryDTO createDormitoryDTO) {

        Dormitory dormitory = new Dormitory(null, createDormitoryDTO.getAddress(), null, null, createDormitoryDTO.getMaxStudent());

        dormitoryMapper.insert(dormitory);

        return BaseResponse.success("宿舍新建成功");
    }

    /*
    宿舍报修
     */
    @Override
    public BaseResponse repair(HttpServletRequest httpServletRequest, RepairSupervisorDTO repairEmployeeDTO) {

        Dormitory dormitory = dormitoryMapper.selectById(repairEmployeeDTO.getIdDormitory());


        if (!StringUtils.hasText(dormitory.getRepair())) {

            dormitory.setRepair(repairEmployeeDTO.getRepairContent());

        } else {

            String beforeContent = dormitory.getRepair();

            dormitory.setRepair(beforeContent + repairEmployeeDTO.getRepairContent());
        }
        dormitoryMapper.updateById(dormitory);

        return BaseResponse.success(dormitory);
    }

    /*
    宿舍违纪情况
     */

    @Override
    public BaseResponse violation(HttpServletRequest httpServletRequest, ViolationSupervisorDTO violationEmployeeDTO) {

        Dormitory dormitory = dormitoryMapper.selectById(violationEmployeeDTO.getIdDormitory());


        if (!StringUtils.hasText(dormitory.getViolation())) {

            dormitory.setViolation(violationEmployeeDTO.getViolationStatus());

            dormitoryMapper.updateById(dormitory);

            return BaseResponse.success(dormitory);
        }

        dormitory.setViolation(violationEmployeeDTO.getViolationStatus() + dormitory.getViolation());

        dormitoryMapper.updateById(dormitory);

        return BaseResponse.success(dormitory);
    }

    /*
    根据学生学号id查询宿舍
     */

    @Override
    public BaseResponse getDormitoryByStudentId(HttpServletRequest httpServletRequest, Integer studentId) {

        Dormitory dormitoryByStudentId = employeeMapper.findDormitoryByStudentId(studentId);

        return BaseResponse.success(dormitoryByStudentId);
    }

    /*
    查看留校的学生名单
     */
    @Override
    public BaseResponse getLeaveAllStudent(HttpServletRequest httpServletRequest) {

        List<Student> students = employeeMapper.findInSchoolStudent();

        return BaseResponse.success(students);
    }

    /*
    给辅导员评分
     */

    @Override
    public BaseResponse scoreCounselor(HttpServletRequest httpServletRequest, Integer counselorId, Double score) {

        Employee counselor = this.getById(counselorId);

        counselor.setScore(score);

        this.updateById(counselor);

        return BaseResponse.success(counselor);//还没有脱敏
    }

    @Override
    public BaseResponse queryStudentById(Integer id) {

        Student student = studentService.getById(id);

        StudentCardVo studentCardVo = BeanCopyUtils.copyBean(student, StudentCardVo.class);

        return BaseResponse.success(studentCardVo);
    }

    @Override
    public BaseResponse updateStudent(UpdateStudentByEmpDTO updateStudentByEmpDTO, HttpServletRequest httpServletRequest) {

        Student student = BeanCopyUtils.copyBean(updateStudentByEmpDTO, Student.class);

        Date date = new Date();

        student.setUpdateTime(date);

        studentService.updateById(student);

        return BaseResponse.success("修改成功");
    }

    @Override
    public BaseResponse queryStudentList(Integer pageNum, Integer pageSize) {

        // 创建分页对象
        Page<Student> page = new Page<>(pageNum, pageSize);

        // 执行分页查询
        IPage<Student> studentPage = studentMapper.selectPage(page, null);

        /*queryAllStudentVo queryAllStudentVo = BeanCopyUtils.copyBean(studentPage, queryAllStudentVo.class);
        queryAllStudentVo.setRows(page.getRecords());*/

        return BaseResponse.success(studentPage);
    }

    /*
    退出登录
     */

    @Override
    public BaseResponse logout(HttpServletRequest httpServletRequest) {
        template.delete(httpServletRequest.getHeader("token"));
        return BaseResponse.success("退出成功");
    }
}
