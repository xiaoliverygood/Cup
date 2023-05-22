package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairSupervisorDTO {

    /*
    宿舍id
     */
    private Integer idDormitory;

    /*
    报修内容
     */
    private String repairContent;

}
