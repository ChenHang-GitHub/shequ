package com.csh.community.controller;

import com.csh.community.dao.CommentInfoMapper;
import com.csh.community.dto.CommentInfoDTO_FromPage;
import com.csh.community.dto.MessageDTO;
import com.csh.community.exception.CustomizeErrorCode;
import com.csh.community.pojo.CommentInfo;
import com.csh.community.pojo.User;
import com.csh.community.service.CommentService;
import com.csh.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    public  Object test (@RequestBody CommentInfoDTO_FromPage commentInfoDTOFromPage,
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
        commentService.insertCommentService(commentInfo);
        //让回复数+1

        questionService.incCommentCount(commentInfoDTOFromPage.getParentId().intValue());
        return  commentInfo;
    }


}
