package com.example;

import com.example.common.BaseResponse;
import com.example.mapper.EmployeeMapper;
import com.example.model.entity.CourseBeUseSeearch;
import com.example.model.entity.Student;
import com.example.service.CourseService;
import com.example.service.StudentService;
import com.example.utils.CaptchaUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SoftwareCupApplicationTests {

	@Resource
	StudentService studentService;
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	CourseService courseService;
	@Test
	void contextLoads() {
//		System.out.println(employeeMapper.getCourseScoreList(5));
//		System.out.println(courseService.getCourseList());
//		List<CourseBeUseSeearch> courseBeUseSeearchScoreList = employeeMapper.getCourseScoreList(5);
//		System.out.println(BaseResponse.success(courseBeUseSeearchScoreList));
//		CaptchaUtil.EmailAndCode.put("email","qwq");
//		CaptchaUtil.EmailAndCode.put("email","qwwwww");
//		System.out.println(CaptchaUtil.EmailAndCode.get("email"));

//		employeeMapper.insertStudentLinkCourse(4,5);
		System.out.println(courseService.getCourseList());

	}


}
