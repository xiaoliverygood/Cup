package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.LinkStuEmpMapper;
import com.example.model.entity.LinkStuEmp;
import com.example.service.LinkStuEmpService;
import org.springframework.stereotype.Service;

/**
 * (LinkStuEmp)表服务实现类
 *
 * @author makejava
 * @since 2023-05-21 15:58:02
 */
@Service("linkStuEmpService")
public class LinkStuEmpServiceImpl extends ServiceImpl<LinkStuEmpMapper, LinkStuEmp> implements LinkStuEmpService {

}

