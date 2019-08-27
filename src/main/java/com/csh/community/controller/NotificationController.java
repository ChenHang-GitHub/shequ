package com.csh.community.controller;

import com.csh.community.dao.CommentInfoMapper;
import com.csh.community.dao.NotificationMapper;
import com.csh.community.pojo.CommentInfo;
import com.csh.community.pojo.Notification;
import com.csh.community.pojo.User;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {

    @Resource
    NotificationMapper notificationMapper;

    @Resource
    CommentInfoMapper commentInfoMapper;

    @GetMapping(value = "/notification/{id}")
    public String changeNotification(@PathVariable Integer id, HttpServletRequest request,
                                     Model model
                                 ) {

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            //处理异常
            return "redirect:/";
        }
        System.out.println("= \"/notification/{id}\")" + id);

        Notification notification = notificationMapper.getNotiById(id);
        if (notification != null) {
            notification.setStatus(1);
            notificationMapper.updateNotiById(notification);
        }

        if (notification.getType() == 1) {
            //回复问题
            return "redirect:/question/" + notification.getOuterid();
        } else {
            //回复评论
            CommentInfo commentInfo = commentInfoMapper.getCommentInfoByNotiId(notification.getOuterid());
            Long questionId = commentInfo.getParentId();

            return "redirect:/question/" + questionId;

        }


    }

}
