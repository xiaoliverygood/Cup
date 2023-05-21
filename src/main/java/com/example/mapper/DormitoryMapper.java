package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.entity.Dormitory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * (Dormitory)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-20 21:55:30
 */
@Mapper
public interface DormitoryMapper extends BaseMapper<Dormitory> {

}

