package com.example.mapper;

import com.example.model.entity.CourseBeUseSeearch;
import com.example.model.entity.Dormitory;
import com.example.model.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.entity.Student;
import org.apache.ibatis.annotations.*;

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

    /*
    查找留在学校的学生
     */
    @Select("SELECT * FROM student WHERE in_school=1")
    List<Student> findInSchoolStudent();

    /*
    添加课程
     */
    @Insert("INSERT INTO course (class_name) VALUES (#{className})")
    void addCourse(@Param("className") String className);


    /*
    根据学号查询成绩，
     */

    @Select("SELECT course.class_name,link_stu_course.student_score FROM link_stu_course LEFT JOIN course ON course.id=link_stu_course.class_id WHERE link_stu_course.student_id=#{studentId}")
    List<CourseBeUseSeearch> getCourseScoreList(@Param("studentId")Integer studentId);

    /*
    通过学生id，课程id进行课程的添加绑定
     */

    @Insert("INSERT INTO link_stu_course( `class_id`, `student_id`, `student_score`) VALUES ( #{classId}, #{studentId},null)")
    void insertStudentLinkCourse(@Param("classId") Integer classId, @Param("studentId") Integer studentId);

    @Delete("DELETE FROM link_stu_dorm WHERE stu_id=#{studentId}")
    void deleteStudentLinkDorm(@Param("studentId") Integer studentId);

    @Select("select stu_id from link_stu_dorm where dorm_id=#{dormId}")
    List<Integer> findStudentManyByDormId(@Param("dormId") Integer dormId);

    @Insert("INSERT INTO link_stu_dorm(`stu_id`,`dorm_id`) VALUES (#{studentId},#{dormId})")
    void insertStudentLinkDorm(@Param("studentId") Integer studentId, @Param("dormId") Integer dormId);
}




