package com.example.model.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (LinkStuEmp)表实体类
 *
 * @author makejava
 * @since 2023-05-21 15:57:59
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("link_stu_emp")
public class LinkStuEmp {
    @TableId
    private Integer stuId;


    private Integer empId;



}

