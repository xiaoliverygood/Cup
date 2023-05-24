package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateDormitoryDTO {
    /**
     * 宿舍地址具体到门牌号
     */
    private String address;
    /**
     * 宿舍最大人数
     */
    private Integer maxStudent;
}
