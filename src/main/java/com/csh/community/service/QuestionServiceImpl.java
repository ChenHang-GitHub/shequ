package com.csh.community.service;

import com.csh.community.dao.PublishMapper;
import com.csh.community.dao.UserMapper;
import com.csh.community.dto.QuestionDTO;
import com.csh.community.pojo.Question;
import com.csh.community.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class QuestionServiceImpl implements QuestionService {

    @Resource
    UserMapper userMapper;
    @Resource
    PublishMapper publishMapper;



    @Override
    public List<QuestionDTO> getList() {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        questions = publishMapper.getAllQuestion();
        for (Question question : questions
                ) {
            User user = userMapper.findById(question.getCreator());
            System.out.println(user + "user????????" + question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //copy
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    @Override
    public List<QuestionDTO> getSelfList(Integer id) {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        // id = #creator
        questions = publishMapper.getQuestionById(id);
        for (Question question : questions
                ) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //copy
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    @Override
    public QuestionDTO getQuestionById(Integer id) {

        QuestionDTO questionDTO = new QuestionDTO();

        Question question = publishMapper.getPersonalQuestion(id);

        BeanUtils.copyProperties(question, questionDTO);

        User user = userMapper.findById(question.getCreator());

        questionDTO.setUser(user);
        return questionDTO;
    }

    @Override
    public void incViewCount(Integer id) {

        publishMapper.incViewCnt(id);


    }

    @Override
    public void incCommentCount(Integer id) {
        publishMapper.incCommentCnt(id);
    }

    @Override
    public void incComment_commentCount(int i) {
        publishMapper.incComment_commentCnt(i);
    }
//  相关问题
    @Override
    public List<Question> getRelatedList(QuestionDTO questionDTO) {
        System.out.println("test tag " + questionDTO.getTag());
        if(StringUtils.isEmpty(questionDTO.getTag()))
        {
            System.out.println("tag is empty");
            return  new ArrayList<Question>();
        }

        // 对tag 进行处理    SpingBoot|spirng|mvc
        String[] tags = StringUtils.split(questionDTO.getTag(),",");
        String regexp = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question = new Question();
        question.setTag(regexp);
        question.setId(questionDTO.getId());
        List<Question> questionList = publishMapper.getRelatedQuestion(question);
        return questionList;
    }

    @Override
    public List<QuestionDTO> getListBySearch(String search) {
        String[] tags = StringUtils.split(search," ");
        String regexp = Arrays.stream(tags).collect(Collectors.joining("|"));

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        // id = #creator
        questions = publishMapper.getQuestionBySearch(regexp);
        for (Question question : questions
                ) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //copy
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
