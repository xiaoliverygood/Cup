package com.example.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DormitoryVo {

    private Integer id;
    //地址
    private String address;
    //宿舍违纪
    private String violation;
    //报修
    private String repair;

}
