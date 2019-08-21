package com.csh.community.service;

import com.csh.community.dto.QuestionDTO;

import java.util.List;

public interface QuestionService {

    List<QuestionDTO> getList();

    List<QuestionDTO> getSelfList(Integer id);
}
