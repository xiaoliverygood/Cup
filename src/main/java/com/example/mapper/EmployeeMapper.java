package com.example.mapper;

import com.example.model.entity.Dormitory;
import com.example.model.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.mail.MailSessionDefinition;
import java.util.List;

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

    @Select("select student.* FROM link_stu_emp LEFT JOIN student ON student.id=link_stu_emp.stu_id WHERE link_stu_emp.emp_id=#{teacherId}")
    List<Student> findStudentByTeacherId(@Param("teacherId") Integer teacherId);

    /*
    多表查询，通过学生的id号查到宿舍号（使用外连接）
     */

    @Select("SELECT dormitory.* FROM link_stu_dorm LEFT JOIN dormitory ON dormitory.id=link_stu_dorm.dorm_id WHERE stu_id=#{studentId}")
    Dormitory findDormitoryByStudentId(@Param("studentId") Integer studentId);
}




