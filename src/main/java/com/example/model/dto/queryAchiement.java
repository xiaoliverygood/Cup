package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class queryAchiement {

    private Integer id;
    //分数1
    private String english;
    //分数2
    private String math;
    //分数3
    private String dataStruct;
    //绩点
    private Double pga;

}
