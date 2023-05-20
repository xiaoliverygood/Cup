package com.example.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.BaseResponse;
import com.example.mapper.StudentMapper;
import com.example.model.dto.RegisterStudentDTO;
import com.example.service.StudentService;
import com.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

/**
* @author L
* @description 针对表【student】的数据库操作Service实现
* @createDate 2023-05-20 20:23:45
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Override
    public BaseResponse register(RegisterStudentDTO registerStudentDTO) {
        if(registerStudentDTO.getEmail())

        Student student = BeanCopyUtils.copyBean(registerStudentDTO, Student.class);

    }
}




