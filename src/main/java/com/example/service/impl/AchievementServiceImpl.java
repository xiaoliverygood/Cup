package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.AchievementMapper;
import com.example.model.entity.Achievement;
import com.example.service.AchievementService;
import org.springframework.stereotype.Service;

/**
 * (Achievement)表服务实现类
 *
 * @author makejava
 * @since 2023-05-20 17:05:08
 */
@Service("achievementService")
public class AchievementServiceImpl extends ServiceImpl<AchievementMapper, Achievement> implements AchievementService {

}

