package com.example;

import com.example.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SoftwareCupApplicationTests {

	@Autowired
	StudentMapper studentMapper;
	@Test
	void contextLoads() {

	}

}
