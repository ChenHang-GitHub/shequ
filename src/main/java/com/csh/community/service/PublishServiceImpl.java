package com.csh.community.service;

import com.csh.community.dao.PublishMapper;
import com.csh.community.pojo.Question;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PublishServiceImpl implements PublishService {

    @Resource
    PublishMapper publishMapper;
    @Override
    public void createService(Question question) {
        publishMapper.createMapper(question);
    }

    @Override
    public void updateQuestionById(Question question) {

        publishMapper.update(question);
    }
}
