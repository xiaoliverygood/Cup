package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.entity.Student;

/**
* @author L
* @description 针对表【student】的数据库操作Mapper
* @createDate 2023-05-20 20:20:47
* @Entity com.example.model.entity.Student
 *
 * 这里不用注解@Mapper注册为bean，并且映射到sql语句是因为继承的BaseMapper已经注入了
*/
public interface StudentMapper extends BaseMapper<Student>{
//    @Select("select * from student where id=#{id}")
//    public Student getStudentById(@Param("id") int id);//测试是否是mybatisplus的问题


}




