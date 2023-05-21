package com.example;

import com.example.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class SoftwareCupApplicationTests {

	@Resource
	StudentService studentService;
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Test
	void contextLoads() {
//	studentMapper.selectById(1);
//		Integer a=1;
//		Student student = new Student();
//		student.setId(2);
//		System.out.println(studentService.getById(1));
		//System.out.println(studentMapper.getStudentById(1));
	//	stringRedisTemplate.opsForValue().set("wwuwww","大家环球");//测试redis


	}

}
