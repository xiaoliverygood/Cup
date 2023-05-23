package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViolationSupervisorDTO {

    /**
    宿舍号
     */
    private Integer idDormitory;

    /**
    违纪情况
     */
    private String violationStatus;
}
