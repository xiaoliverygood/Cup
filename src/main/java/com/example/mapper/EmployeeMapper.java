package com.example.mapper;

import com.example.model.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.mail.MailSessionDefinition;

/**
* @author L
* @description 针对表【employee】的数据库操作Mapper
* @createDate 2023-05-21 19:40:06
* @Entity com.example.model.entity.Employee
*/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    @Select("select * from employee where email=#{email}")
    Employee findEmployeeByEmail(@Param("email") String email);

//    @Select()
}




