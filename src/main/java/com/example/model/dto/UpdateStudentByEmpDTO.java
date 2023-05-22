package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentByEmpDTO {

    private Integer id;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生年龄
     */
    private Integer age;

    /**
     *
     */
    private String sex;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 余额
     */
    private Double balance;

    /**
     * 专业
     */
    private String speciality;

    /**
     * 班级
     */
    private String myClass;

    /**
     * 奖惩
     */
    private String reawrdPunish;

    /**
     * (0:未入学,1:在读,2:已毕业)
     */
    private Integer checkIn;

    /**
     * 生源地
     */
    private String origin;

}
