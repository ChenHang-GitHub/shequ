package com.csh.community.controller;

import com.csh.community.dto.QuestionDTO;
import com.csh.community.pojo.Question;
import com.csh.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

@Controller
public class QuestionController {


    @Resource
    QuestionService questionService;

    @GetMapping(value = "/question/{id}")
    public  String question(@PathVariable Integer id , Model model)
    {

        QuestionDTO questionDTO= questionService.getQuestionById(id);
        //每次访问问题页面 都让浏览数+1 当 访问登录用户自己的页面不+1；
        questionService.incViewCount(id);

        model.addAttribute("questionDTO",questionDTO);
        return "question";
    }
}
