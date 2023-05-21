package com.example.model.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (LinkStuDorm)表实体类
 *
 * @author makejava
 * @since 2023-05-20 22:17:56
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("link_stu_dorm")
public class LinkStuDorm {
    @TableId
    private Integer stuId;


    private Integer dormId;



}

