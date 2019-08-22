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
public class PersonalQuestionController {


    @Resource
    QuestionService questionService;

    @GetMapping(value = "/question/{id}")
    public  String question(@PathVariable Integer id , Model model)
    {

        QuestionDTO questionDTO= questionService.getQuestionById(id);
        model.addAttribute("questionDTO",questionDTO);
        return "question";
    }
}
