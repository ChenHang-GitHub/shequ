package com.csh.community.controller;

import com.csh.community.dao.PublishMapper;
import com.csh.community.dao.UserMapper;
import com.csh.community.dto.QuestionDTO;
import com.csh.community.pojo.Question;
import com.csh.community.service.QuestionService;
import com.csh.community.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/*
GET：读取（Read）
POST：新建（Create）
PUT：更新（Update）
PATCH：更新（Update）
DELETE：删除（Delete）
* */
@Controller
@Cacheable(cacheNames = "index")
public class IndexController {

    Logger logger =  LoggerFactory.getLogger(IndexController.class);


    @Resource
    UserMapper userMapper;
    @Resource
    QuestionService questionService;
    @Resource
    PublishMapper publishMapper;

    @Resource
    UserService userService;

    @GetMapping (value = {"/","/page"})
    public  String test (Model model,
                         HttpServletRequest request,
                         @RequestParam (name = "pageNum",defaultValue = "1" ) int pageNum,
                         @RequestParam (name = "search",required = false) String search)
    {


        if(!StringUtils.isBlank(search) ||!StringUtils.isEmpty(search))
        {
            //搜索功能
            Page<Object> pageHelper = PageHelper.startPage(pageNum, 5);
            List<QuestionDTO>  questionDTOList =  questionService.getListBySearch(search);
            int pages = pageHelper.getPages();
            int pageNum1 = pageHelper.getPageNum();

            PageInfo pageInfo = new PageInfo(questionDTOList);
            int  arr [] = new int[pages];
            for(int i =0;i<pages;i++)
            {
                arr[i]= i+1;
            }
            model.addAttribute("search",search);
            model.addAttribute("midpage",arr);
            model.addAttribute("pages",pages);
            model.addAttribute("questionDTO",questionDTOList);
            return  "index";
        }else {
            logger.debug("pageNum = ?........ " + pageNum);
            //每次登入首页的时候 查询出首页信息
            List<QuestionDTO> questionDTOList = new ArrayList<>();
            //pagehelper
            Page<Object> pageHelper = PageHelper.startPage(pageNum, 7);
            questionDTOList = questionService.getList();
            int pages = pageHelper.getPages();
            int pageNum1 = pageHelper.getPageNum();

            PageInfo pageInfo = new PageInfo(questionDTOList);
            int arr[] = new int[pages];
            for (int i = 0; i < pages; i++) {
                arr[i] = i + 1;
            }
            List<Question> hotQuestionList = publishMapper.getHotQuestion();
            model.addAttribute("hotQuestionList",hotQuestionList);
            model.addAttribute("midpage", arr);
            model.addAttribute("pages", pages);
            model.addAttribute("questionDTO", questionDTOList);
            return "index";
        }
    }

}
