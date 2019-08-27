package com.csh.community.controller;

import com.csh.community.dao.NotificationMapper;
import com.csh.community.dao.UserMapper;
import com.csh.community.dto.NotificationDTO;
import com.csh.community.dto.QuestionDTO;
import com.csh.community.pojo.User;
import com.csh.community.service.NotificationService;
import com.csh.community.service.QuestionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
/*
* 我的提问功能实现*/
@Controller
public class PersonalController {


    Logger logger = LoggerFactory.getLogger(PersonalController.class);



    @Resource
    QuestionService questionService;

    @Resource
    NotificationService notificationService;
    @Resource
    UserMapper userMapper;

//    @GetMapping(value = "/personal")
//    public String toPersonalPage(Model model) {
//
//        model.addAttribute("headingName","我的提问");
//        return "personal";
//    }

    @Resource
    NotificationMapper notificationMapper;

    @GetMapping(value = {"/personal/{action}","/personalpage"})
    public String PersonalPage(@PathVariable String action,
                               Model model,
                               HttpServletRequest request,
                               @RequestParam(name = "pageNum",defaultValue = "1" ) int pageNum)
    {

        logger.warn(pageNum+"");
        System.out.println(action+"action=?....................");
        if(action.equals("questions"))
        {
            model.addAttribute("headingName","我的提问");
            //
            User user = (User) request.getSession().getAttribute("user");
            //每次登入首页的时候 查询出首页信息
            List<QuestionDTO> questionDTOList =  new ArrayList<>();
            //pagehelper
            Page<Object> pageHelper = PageHelper.startPage(pageNum, 3);
            questionDTOList= questionService.getSelfList(user.getId());
            logger.debug("questionDTOList????"+questionDTOList.toString());
            int pages = pageHelper.getPages();
            int pageNum1 = pageHelper.getPageNum();
            logger.debug("pages........."+pages+pageNum1);

            PageInfo pageInfo = new PageInfo(questionDTOList);
            int  arr [] = new int[pages];
            for(int i =0;i<pages;i++)
            {
                arr[i]= i+1;
            }
            model.addAttribute("midpage",arr);
            model.addAttribute("pages",pages);
            model.addAttribute("questionDTO",questionDTOList);
            logger.debug("personal"+questionDTOList.toString());
        }else if(action.equals("replies"))
        {
            model.addAttribute("headingName","我的通知");
            //添加回复内容   A—》question ;

            User user = (User)request.getSession().getAttribute("user");
            Page<Object> pageHelper = PageHelper.startPage(pageNum, 6);
            List<NotificationDTO> notificationDTOList = notificationService.getByReceiverId(user.getId());
            int pages = pageHelper.getPages();
            int pageNum1 = pageHelper.getPageNum();
            PageInfo pageInfo = new PageInfo(notificationDTOList);
            int  arr [] = new int[pages];
            for(int i =0;i<pages;i++)
            {
                arr[i]= i+1;
            }
            model.addAttribute("midpage",arr);
            model.addAttribute("pages",pages);

            model.addAttribute("notifications",notificationDTOList);
//            int unReadCou nt =  notificationMapper.countUnReadCount(user.getId());
//            System.out.println("unReadCountunReadCountunReadCountunReadCountunReadCount"+unReadCount);
//            model.addAttribute("unReadCount",unReadCount);
//            request.getSession().setAttribute("unReadCount",unReadCount);

        }


        return "personal";
    }



}
