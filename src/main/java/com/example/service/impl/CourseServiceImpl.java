package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.model.entity.Course;
import com.example.service.CourseService;
import com.example.mapper.CourseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author L
* @description 针对表【course】的数据库操作Service实现
* @createDate 2023-05-23 09:37:12
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService{

    /*
    获取所有课程
     */
    @Override
    public List<Course> getCourseList() {
        return this.getCourseList();
    }
}




