package com.csh.community.service;

import com.csh.community.dto.QuestionDTO;
import com.csh.community.pojo.Question;

import java.util.List;

public interface QuestionService {

    List<QuestionDTO> getList();

    List<QuestionDTO> getSelfList(Integer id);

    QuestionDTO getQuestionById(Integer id);

    void incViewCount(Integer id);

    void incCommentCount(Integer id);

    void incComment_commentCount(int i);

    List<Question> getRelatedList(QuestionDTO questionDTO);
}
