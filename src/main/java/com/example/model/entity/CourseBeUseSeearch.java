package com.example.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author L
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseBeUseSeearch {
    /*
    课程名称
     */
    private String className;

    /*
    课程分数
     */
    private Double studentScore;
}
