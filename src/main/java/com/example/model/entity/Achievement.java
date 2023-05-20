package com.example.model.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Achievement)表实体类
 *
 * @author makejava
 * @since 2023-05-20 17:05:05
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("achievement")
public class Achievement  {
    //学生id@TableId
    @TableId
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

