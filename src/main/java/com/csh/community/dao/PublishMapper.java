package com.csh.community.dao;


import com.csh.community.pojo.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PublishMapper {


    void createMapper(Question question);

    List<Question> getAllQuestion();

    List<Question> getQuestionById(Integer id);


    Question getPersonalQuestion(Integer id);

    void incViewCnt(Integer id);

    void incCommentCnt(Integer id);
}
