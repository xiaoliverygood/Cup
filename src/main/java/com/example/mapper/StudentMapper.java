package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.entity.Student;
import org.apache.ibatis.annotations.Mapper;

/**
* @author L
* @description 针对表【student】的数据库操作Mapper
* @createDate 2023-05-20 20:20:47
* @Entity com.example.model.entity.Student
*/
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}




