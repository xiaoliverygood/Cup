package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentMessageDTO {
    /**
     * 增加班级信息，生源地，入学
     */
    /*
    学生id
     */

    private Integer studentId;
    /*
    班级名称
     */
    private String className;
    /*
    生源地
     */

    private String origin;

    /*
    专业
     */
    private String speciality;

}
