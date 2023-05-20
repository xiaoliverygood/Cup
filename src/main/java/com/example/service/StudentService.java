package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.BaseResponse;
import com.example.model.dto.RegisterStudentDTO;
import org.springframework.web.bind.annotation.RequestBody;

/**
* @author L
* @description 针对表【student】的数据库操作Service
* @createDate 2023-05-20 20:23:45
*/
public interface StudentService extends IService<Student> {
    BaseResponse register(@RequestBody RegisterStudentDTO registerStudentDTO);

}
