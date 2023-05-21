package com.example.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.AppHttpCodeEnum;
import com.example.common.BaseResponse;
import com.example.mapper.StudentMapper;
import com.example.model.dto.LoginStudentDTO;
import com.example.model.dto.RegisterStudentDTO;
import com.example.model.entity.Student;
import com.example.service.StudentService;
import com.example.utils.BeanCopyUtils;
import com.example.utils.CaptchaUtil;
import com.example.utils.EmailRegularExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


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

            template.opsForValue().set(token,loginStudentDTO.getEmail());

            return BaseResponse.success(token);//登录成功后只返回token


        }else {
            return BaseResponse.Error(AppHttpCodeEnum.LOGIN_ERROR,loginStudentDTO);
        }
    }
}




