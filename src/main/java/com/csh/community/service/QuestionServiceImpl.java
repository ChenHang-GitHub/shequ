package com.csh.community.service;

import com.csh.community.dao.PublishMapper;
import com.csh.community.dao.UserMapper;
import com.csh.community.dto.QuestionDTO;
import com.csh.community.pojo.Question;
import com.csh.community.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    UserMapper userMapper;
    @Resource
    PublishMapper publishMapper;


    @Override
    public List<QuestionDTO> getList() {
        List<QuestionDTO> questionDTOList =  new ArrayList<>();
        List<Question> questions =  new ArrayList<>();
        questions = publishMapper.getAllQuestion();
        for (Question question:questions
             ) {
            User user =userMapper.findById(question.getCreator());
            System.out.println(user+"user????????"+question.getCreator());
            QuestionDTO questionDTO =  new QuestionDTO();
            //copy
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
            System.out.println(questionDTO);
        }
        return questionDTOList;
    }

    @Override
    public List<QuestionDTO> getSelfList(Integer id) {
        List<QuestionDTO> questionDTOList =  new ArrayList<>();
        List<Question> questions =  new ArrayList<>();
        questions = publishMapper.getQuestionById(id);
        for (Question question:questions
                ) {
            User user =userMapper.findById(question.getCreator());
            QuestionDTO questionDTO =  new QuestionDTO();
            //copy
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
