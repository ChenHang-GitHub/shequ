package com.csh.community.controller;

import com.csh.community.dto.CommentInfoDTO_ToPage;
import com.csh.community.dto.QuestionDTO;
import com.csh.community.pojo.Question;
import com.csh.community.service.CommentService;
import com.csh.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Controller
public class QuestionController {


    @Resource
    QuestionService questionService;

    @Resource
    CommentService commentService;

    @GetMapping(value = "/question/{id}")
    public  String question(@PathVariable Integer id , Model model)
    {

        QuestionDTO questionDTO= questionService.getQuestionById(id);
        //每次访问问题页面 都让浏览数+1 当 访问登录用户自己的页面不+1；
        questionService.incViewCount(id);

        List<CommentInfoDTO_ToPage> commentList =  commentService.getCommentInfoDTOList(id);
//        Collections.sort(commentList);
        model.addAttribute("questionDTO",questionDTO);
        //返回1ji评论
        model.addAttribute("commentLists",commentList);
        return "question";
    }
}
