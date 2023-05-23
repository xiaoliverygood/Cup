package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.BaseResponse;
import com.example.model.dto.FindPasswordDTO;
import com.example.model.dto.LoginStudentDTO;
import com.example.model.dto.RegisterStudentDTO;
import com.example.model.dto.UpdateStudentDTO;
import com.example.model.entity.Student;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
* @author L
* @description 针对表【student】的数据库操作Service
* @createDate 2023-05-20 20:23:45
*/

public interface StudentService extends IService<Student> {
    BaseResponse register(@RequestBody RegisterStudentDTO registerStudentDTO);

    BaseResponse login(@RequestBody LoginStudentDTO loginStudentDTO);

    BaseResponse updateInfo(UpdateStudentDTO updateStudentDTO, HttpServletRequest httpServletRequest);

    BaseResponse queryInfo(HttpServletRequest httpServletRequest);

    /*
    找回密码
     */
    BaseResponse findPassword(@RequestBody FindPasswordDTO findPasswordDTO);
    /*
    退出登录
     */

    BaseResponse logout(HttpServletRequest httpServletRequest);
}
