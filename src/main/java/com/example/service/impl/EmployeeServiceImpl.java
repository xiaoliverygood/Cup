package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.model.entity.Employee;
import com.example.service.EmployeeService;
import com.example.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

/**
* @author L
* @description 针对表【employee】的数据库操作Service实现
* @createDate 2023-05-21 19:40:06
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{

}




