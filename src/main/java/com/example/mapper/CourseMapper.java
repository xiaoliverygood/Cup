package com.example.mapper;

import com.example.model.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author L
* @description 针对表【course】的数据库操作Mapper
* @createDate 2023-05-23 09:37:12
* @Entity com.example.model.entity.Course
*/
public interface CourseMapper extends BaseMapper<Course> {

    @Select("select * from course")
    List<Course> findAll();

}




