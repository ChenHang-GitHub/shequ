package com.csh.community.controller;

import com.csh.community.dao.CommentInfoMapper;
import com.csh.community.dto.CommentInfoDTO_FromPage;
import com.csh.community.dto.CommentInfoDTO_ToPage;
import com.csh.community.dto.MessageDTO;
import com.csh.community.exception.CustomizeErrorCode;
import com.csh.community.pojo.CommentInfo;
import com.csh.community.pojo.User;
import com.csh.community.service.CommentService;
import com.csh.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {


    @Resource
    CommentInfoMapper commentInfoMapper;


    @Resource
    CommentService commentService;

    @Resource
    QuestionService questionService;


    @PostMapping(value = "/comment")
    @ResponseBody
    @Transactional
    public  Object insertTypeOneComment (@RequestBody CommentInfoDTO_FromPage commentInfoDTOFromPage,
                         HttpServletRequest request)
    {
        User user = (User)request.getSession().getAttribute("user");
        //判断用户登入   (异常处理)
        if(user==null)
        {
            System.out.println("comment :user is null");
//            return MessageDTO.errorof(1000,"未登录");
            return MessageDTO.errorof(CustomizeErrorCode.USER_NOT_LOGIN);
        }

        if(commentInfoDTOFromPage.getContent()==null || commentInfoDTOFromPage.getContent().equals(""))
        {
            return MessageDTO.errorof(CustomizeErrorCode.CONTENT_IS_NULL);
        }

        System.out.println("Test JSON"+ commentInfoDTOFromPage.toString());
        CommentInfo commentInfo =new CommentInfo();

        commentInfo.setParentId(commentInfoDTOFromPage.getParentId());
        commentInfo.setContent(commentInfoDTOFromPage.getContent());
        commentInfo.setType(commentInfoDTOFromPage.getType());
        commentInfo.setGmtCreate(System.currentTimeMillis());
        commentInfo.setGmtModify(System.currentTimeMillis());
        commentInfo.setCommentator(user.getId());

        commentInfo.setLikeCount(0L);

        //插入回复的消息
        commentService.insertCommentService(commentInfo,user);
        //让回复数+1
        /*
         1:回复了问题
         2:回复了评论
        * */
        if(commentInfo.getType()==1)
        {
            questionService.incCommentCount(commentInfoDTOFromPage.getParentId().intValue());
        }else if(commentInfo.getType()==2)
        {
            System.out.println("type=============================22222222");
            questionService.incComment_commentCount(commentInfoDTOFromPage.getParentId().intValue());
        }

        return  commentInfo;
    }





/*
* 获取二级评论*/
    @ResponseBody
    @GetMapping(value = "/comment/{id}")
    public Object comments(@PathVariable(name = "id") int id) {
        int type  = 2;
        List<CommentInfoDTO_ToPage> commentInfoDTO_toPages = commentService.getTypeTwoCommentList(id,type);
        return commentInfoDTO_toPages;
    }

}
