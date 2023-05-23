package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.entity.Course;

import java.util.List;

/**
* @author L
* @description 针对表【course】的数据库操作Service
* @createDate 2023-05-23 09:37:12
*/
public interface CourseService extends IService<Course> {
/*
获取所有课程
 */
    List<Course> getCourseList();
}
