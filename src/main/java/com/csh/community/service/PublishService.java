package com.csh.community.service;

import com.csh.community.pojo.Question;

public interface PublishService {
    void createService(Question question);

    void updateQuestionById(Question question);
}
