package com.example.model.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Dormitory)表实体类
 *
 * @author makejava
 * @since 2023-05-20 21:55:31
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("dormitory")
public class Dormitory {
    @TableId
    private Integer id;

    //地址
    private String address;
    //宿舍违纪
    private String violation;
    //报修
    private String repair;
    //最大人数
    private Integer max;

}

