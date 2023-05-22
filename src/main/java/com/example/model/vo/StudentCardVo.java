package com.example.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCardVo {

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
     * 专业
     */
    private String speciality;

    /**
     * 班级
     */
    private String myClass;

    /**
     * (0:未入学,1:在读,2:已毕业)
     */
    private Integer checkIn;

    /**
     * 生源地
     */
    private String origin;

}
