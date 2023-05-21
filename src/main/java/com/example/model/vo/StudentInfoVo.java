package com.example.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfoVo {

    private StudentVo student;

    private DormitoryVo dormitory;

    private List<StuEmpInfoVo> stuEmpInfoVoList;

}
