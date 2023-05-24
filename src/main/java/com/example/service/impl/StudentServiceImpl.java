package com.example.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.AppHttpCodeEnum;
import com.example.common.BaseResponse;
import com.example.mapper.DormitoryMapper;
import com.example.mapper.LinkStuDormMapper;
import com.example.mapper.StudentMapper;
import com.example.model.dto.FindPasswordDTO;
import com.example.model.dto.LoginStudentDTO;
import com.example.model.dto.RegisterStudentDTO;
import com.example.model.dto.UpdateStudentDTO;
import com.example.model.entity.*;
import com.example.model.vo.DormitoryVo;
import com.example.model.vo.StuEmpInfoVo;
import com.example.model.vo.StudentInfoVo;
import com.example.model.vo.StudentVo;
import com.example.service.EmployeeService;
import com.example.service.LinkStuEmpService;
import com.example.service.StudentService;
import com.example.utils.*;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
* @author L
* @description 针对表【student】的数据库操作Service实现
* @createDate 2023-05-20 20:23:45
*/
@Service//使用IOC容器，自己必须是容器里面，也就是注册为bean呢
public class StudentServiceImpl extends ServiceImpl<StudentMapper,Student> implements StudentService {
//不用注入StudentMapper是因为继承这个类已经实现了注入了，并且里面封装了一些方法，所以并不需要注入Mapper了

    @Autowired
    StringRedisTemplate template;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private LinkStuDormMapper linkStuDormMapper;
    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Autowired
    private LinkStuEmpService linkStuEmpService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private GetUserUtils getUserUtils;

    @Override
    public BaseResponse register(RegisterStudentDTO registerStudentDTO) {

        String correctionCode= CaptchaUtil.EmailAndCode.get(registerStudentDTO.getEmail());

        boolean a=registerStudentDTO.getCode().equals(correctionCode);

        if(EmailRegularExpression.RegularEmailPattern(registerStudentDTO.getEmail())&&a){

            Student student = BeanCopyUtils.copyBean(registerStudentDTO, Student.class);

            this.save(student);

            return BaseResponse.success("注册成功，请前往登录！");
        }
        return BaseResponse.Error(AppHttpCodeEnum.REQUIRE_USERNAME,registerStudentDTO);
    }


    @Override
    public BaseResponse login(LoginStudentDTO loginStudentDTO) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getEmail,loginStudentDTO.getEmail());

        boolean a=loginStudentDTO.getPwd().equals(this.getOne(queryWrapper).getPwd());
        if(EmailRegularExpression.RegularEmailPattern(loginStudentDTO.getEmail())&&a){

            String token= UUID.randomUUID().toString();

            Student student=studentMapper.findStudentByEmail(loginStudentDTO.getEmail());

            template.opsForValue().set(token,student.getId().toString());

            return BaseResponse.success(token);//登录成功后只返回token


        }else {
            return BaseResponse.Error(AppHttpCodeEnum.LOGIN_ERROR,loginStudentDTO);
        }
    }

    @Override
    public BaseResponse updateInfo(UpdateStudentDTO updateStudentDTO, HttpServletRequest httpServletRequest) {
        //根据token找到对应学生
        Student student = BeanCopyUtils.copyBean(updateStudentDTO, Student.class);
        //设置对应的学生id
        student.setId(getUserUtils.getId(httpServletRequest));
        //根据id更新到数据库
        updateById(student);

        return BaseResponse.success("修改成功");
    }

    @Override
    public BaseResponse queryInfo(HttpServletRequest httpServletRequest) {



        //学生个人信息**
        Student student = studentMapper.selectById(Integer.valueOf(template.opsForValue().get(httpServletRequest.getHeader("token"))));
        Integer id1 = getUserUtils.getId(httpServletRequest);

        StudentVo studentVo = BeanCopyUtils.copyBean(student, StudentVo.class);

        //学生的id
        Integer id = student.getId();

        LambdaQueryWrapper<LinkStuDorm> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(LinkStuDorm::getStuId,id);

        //查到学生对应的宿舍号
        LinkStuDorm linkStuDorm = linkStuDormMapper.selectOne(queryWrapper);
        Integer dormId = linkStuDorm.getDormId();

        //根据宿舍号查到对应宿舍
        LambdaQueryWrapper<Dormitory> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Dormitory::getId,dormId);

        //返回宿舍信息**
        Dormitory dormitory = dormitoryMapper.selectOne(queryWrapper1);
        DormitoryVo dormitoryVo = BeanCopyUtils.copyBean(dormitory, DormitoryVo.class);

        //学生对应教师
        LambdaQueryWrapper<LinkStuEmp> queryWrapper2 = new LambdaQueryWrapper<>();
        //(关系表)根据学生的id,找到对应的教师,教师数量可能大于1
        queryWrapper2.eq(LinkStuEmp::getStuId,id);

        //LinkStuEmpList为关系表,用来接收一个学生对应的多个教师
        List<LinkStuEmp> LinkStuEmpList = linkStuEmpService.list(queryWrapper2);

        //教师信息集合,将对应的所有教师信息存入该集合**
        List<Employee> empList = new ArrayList<>();
        //根据每一个教师的id,在职工表中查到对应教师信息,存入empList
        for (LinkStuEmp linkStuEmp:LinkStuEmpList) {
            Integer empId = linkStuEmp.getEmpId();
            Employee employee = employeeService.getById(empId);
            empList.add(employee);
        }

        //将教师信息转为voList
        List<StuEmpInfoVo> stuEmpInfoVoList = BeanCopyUtils.copyBeanList(empList, StuEmpInfoVo.class);

        StudentInfoVo studentInfoVo = new StudentInfoVo(studentVo,dormitoryVo,stuEmpInfoVoList);

        //返回学生信息,辅导员(教师信息),宿舍信息
        return BaseResponse.success(studentInfoVo);
    }


    /*
    找回密码
     */

    @Override
    public BaseResponse findPassword(FindPasswordDTO findPasswordDTO) {
        String correctPassword = CaptchaUtil.EmailAndCodeFindpassword.get(findPasswordDTO.getEmail());

        if (correctPassword.equals(findPasswordDTO.getCode())) {

            Student student =studentMapper.findStudentByEmail(findPasswordDTO.getEmail());

            student.setPwd(findPasswordDTO.getNewPassword());

            this.updateById(student);

            CaptchaUtil.EmailAndCodeFindpassword.remove(findPasswordDTO.getEmail());

            return BaseResponse.success(student);

        } else {

            return BaseResponse.Error(AppHttpCodeEnum.NO_OPERATOR_AUTH, findPasswordDTO);
        }
    }

    /*
    退出登录
     */

    @Override
    public BaseResponse logout(HttpServletRequest httpServletRequest) {
        template.delete(httpServletRequest.getHeader("token"));
        return BaseResponse.success("成功退出登录");
    }
}




