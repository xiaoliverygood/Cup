package com.example.service.impl;


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
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    StringRedisTemplate template;

    @Override
    public BaseResponse register(RegisterStudentDTO registerStudentDTO) {

        String correctionCode= CaptchaUtil.EmailAndCode.get(registerStudentDTO.getEmail());

        boolean a=registerStudentDTO.getCode().equals(correctionCode);

        if(EmailRegularExpression.RegularEmailPattern(registerStudentDTO.getEmail())&&a){

            Student student = BeanCopyUtils.copyBean(registerStudentDTO, Student.class);

            studentMapper.updateById(student);

            return BaseResponse.success(student);
        }
        return BaseResponse.Error(AppHttpCodeEnum.REQUIRE_USERNAME,registerStudentDTO);
    }


    @Override
    public BaseResponse login(LoginStudentDTO loginStudentDTO) {
        boolean a=loginStudentDTO.getPwd().equals(studentMapper.selectById(loginStudentDTO.getEmail()).getPwd());
        if(EmailRegularExpression.RegularEmailPattern(loginStudentDTO.getEmail())&&a){

            String token= UUID.randomUUID().toString();

            template.opsForValue().set(token,loginStudentDTO.getEmail());

            return BaseResponse.success(token);//登录成功后只返回token


        }else {
            return BaseResponse.Error(AppHttpCodeEnum.LOGIN_ERROR,loginStudentDTO);
        }
    }
}




