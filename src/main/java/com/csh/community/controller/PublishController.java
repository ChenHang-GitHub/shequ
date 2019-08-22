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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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


    @GetMapping("/publish/{id}")
    public String toEdit(@PathVariable(name = "id") Integer id,
                         Model model) {
        Question question =  publishMapper.getPersonalQuestion(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("desc", question.getDescription());
        model.addAttribute("tag", question.getTag());
        //提供需要修改的id
        model.addAttribute("updateId",question.getId());

        return "publish";
    }


    //更新
    @PutMapping(value = "/publish")
    public  String  updateQuestion(@Param("updateId") Integer updateId,
                                   @Param("title") String title,
                                   @Param("desc") String desc,
                                   @Param("tag") String tag)
    {
        logger.debug("testPutMapping"+updateId+title+tag+desc);
//        publishService.

        return "redirect:/";
    }

    @PostMapping("/publish")
    public String doPublish(@Param("title") String title,
                            @Param("desc") String desc,
                            @Param("tag") String tag,
                            HttpServletRequest request,
                            Model model) {


        model.addAttribute("title", title);
        model.addAttribute("desc", desc);
        model.addAttribute("tag", tag);

        User user = (User) request.getSession().getAttribute("user");
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("token")) {
//                    logger.info("查询Cookie携带token");
//                    String token = cookie.getValue();
//                    //根据token 查询登陆态
//                    user = userMapper.findByToken(token);
//                    logger.info(user.getName() + user.getToken());
//                    if (user != null) {
//                        request.getSession().setAttribute("user", user);
//                    }
//                    break;
//                }
//            }
//        }
        if (user == null) {
            model.addAttribute("error", "用户未登录");
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
