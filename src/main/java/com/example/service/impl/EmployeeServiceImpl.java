package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.AppHttpCodeEnum;
import com.example.common.BaseResponse;
import com.example.model.dto.LoginEmployeeDTO;
import com.example.model.dto.RegisterEmployeeDTO;
import com.example.model.dto.UpdateStudentMessageDTO;
import com.example.model.entity.Employee;
import com.example.model.entity.Employee;
import com.example.model.entity.Student;
import com.example.model.vo.LoginEmployeeVo;
import com.example.service.EmployeeService;
import com.example.mapper.EmployeeMapper;
import com.example.service.StudentService;
import com.example.utils.BeanCopyUtils;
import com.example.utils.CaptchaUtil;
import com.example.utils.EmailRegularExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
* @author L
* @description 针对表【employee】的数据库操作Service实现
* @createDate 2023-05-21 19:40:06
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService{
    @Autowired
    StringRedisTemplate template;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    StudentService studentService;
    @Override
    public BaseResponse register(RegisterEmployeeDTO registerEmployeeDTO) {
        String correctionCode= CaptchaUtil.EmailAndCode.get(registerEmployeeDTO.getEmail());

        boolean a=registerEmployeeDTO.getCode().equals(correctionCode);

        if(EmailRegularExpression.RegularEmailPattern(registerEmployeeDTO.getEmail())&&a){

           Employee employee = BeanCopyUtils.copyBean(registerEmployeeDTO, Employee.class);

            this.save(employee);

            return BaseResponse.success("注册成功，请前往登录！");
        }
        return BaseResponse.Error(AppHttpCodeEnum.REQUIRE_USERNAME,registerEmployeeDTO);
    }

    @Override
    public BaseResponse login(LoginEmployeeDTO loginEmployeeDTO) {
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getEmail,loginEmployeeDTO.getEmail());


        boolean a=loginEmployeeDTO.getPwd().equals(this.getOne(queryWrapper).getPwd());
        if(EmailRegularExpression.RegularEmailPattern(loginEmployeeDTO.getEmail())&&a){

            String token= UUID.randomUUID().toString();

            Employee employee=employeeMapper.findEmployeeByEmail(loginEmployeeDTO.getEmail());


            template.opsForValue().set(token,employee.getId().toString());
            //

            LoginEmployeeVo loginEmployeeVo=new LoginEmployeeVo(employee.getEmail(),employee.getName(),employee.getPhone(),employee.getSex(),employee.getAuthority(),token);

            return BaseResponse.success(loginEmployeeVo);//登录成功后返回Vo对象，里面带token


        }else {
            return BaseResponse.Error(AppHttpCodeEnum.LOGIN_ERROR,loginEmployeeDTO);
        }
    }

    @Override
    public BaseResponse showMyMessage(HttpServletRequest httpServletRequest) {
       Employee employee=employeeMapper.findEmployeeByEmail(template.opsForValue().get(httpServletRequest.getHeader("token")));
       return BaseResponse.success(employee);
    }

    @Override
    public BaseResponse updateStudentMessage(HttpServletRequest httpServletRequest,UpdateStudentMessageDTO updateStudentMessageDTO) {
        Student student=studentService.getById(updateStudentMessageDTO.getStudentId());

        student.setInSchool(1);

        student.setMyClass(updateStudentMessageDTO.getClassName());

        student.setOrigin(updateStudentMessageDTO.getOrigin());

        student.setCheckIn(1);

        student.setSpeciality(updateStudentMessageDTO.getSpeciality());

        studentService.updateById(student);//迎新更新工作

        return BaseResponse.success(student);
    }

//未完成，待商量
    @Override
    public BaseResponse showStudentScore(HttpServletRequest httpServletRequest, Integer studentId) {
       return null;
    }
}




