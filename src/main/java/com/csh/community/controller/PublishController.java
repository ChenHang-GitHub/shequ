package com.csh.community.controller;

import com.csh.community.dao.PublishMapper;
import com.csh.community.dao.UserMapper;
import com.csh.community.pojo.Question;
import com.csh.community.pojo.User;
import com.csh.community.service.PublishService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    Logger logger = LoggerFactory.getLogger(PublishController.class);
    @Resource
    PublishMapper publishMapper;
    @Resource
    PublishService publishService;

    @Resource
    UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@Param("title") String title,
                            @Param("desc") String desc,
                            @Param("tag") String tag,
                            HttpServletRequest request,
                            Model model) {


        model.addAttribute("title",title);
        model.addAttribute("desc",desc);
        model.addAttribute("tag",tag);

        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    logger.info("查询Cookie携带token");
                    String token = cookie.getValue();
                    //根据token 查询登陆态
                    user = userMapper.findByToken(token);
                    logger.info(user.getName() + user.getToken());
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if(user==null)
        {
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(desc);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        publishService.createService(question);
        System.out.println(title + desc + tag);
        return "redirect:/";
    }

}
