package com.example.utils;

import com.example.mapper.DormitoryMapper;
import com.example.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JudgementStudentMany {
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    DormitoryMapper dormitoryMapper;

    public boolean judgeStudentMany(Integer dormId){

        if(employeeMapper.findStudentManyByDormId(dormId).size()>dormitoryMapper.selectById(dormId).getMax()){

            return true;//返回true代表可以进入，人数刚好或者少于

        }else {

            return false;//代表满人了
        }
    }
}
