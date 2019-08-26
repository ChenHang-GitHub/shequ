package com.csh.community.controller;

import com.csh.community.cache.TagCache;
import com.csh.community.dao.PublishMapper;
import com.csh.community.dao.UserMapper;
import com.csh.community.pojo.Question;
import com.csh.community.pojo.User;
import com.csh.community.service.PublishService;
import org.apache.commons.lang3.StringUtils;
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
    public String publish(Model model) {
        model.addAttribute("tags", TagCache.get());
        System.out.println(TagCache.get());
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
        model.addAttribute("updateid",question.getId());
        model.addAttribute("tags", TagCache.get());

        System.out.println(TagCache.get()+"11111111111111111111111111111111111");
        return "publish";
    }


    //更新
    @PutMapping(value = "/publish")
    public  String  updateQuestion(@Param("updateId") Integer updateid,
                                   @Param("title") String title,
                                   @Param("desc") String desc,
                                   @Param("tag") String tag,Model model)
    {
        logger.debug("testPutMapping"+updateid+title+tag+desc);
//        publishService.
        Question question = new Question();
        question.setId(updateid);
        question.setTitle(title);
        question.setTag(tag);
        question.setDescription(desc);
        question.setGmtCreate(System.currentTimeMillis());
        publishService.updateQuestionById(question);


        return "redirect:/";
    }


    @PostMapping("/publish")
    public String doPublish(@Param("title") String title,
                            @Param("desc") String desc,
                            @Param("tag") String tag,
                            HttpServletRequest request,
                            Model model) {

        model.addAttribute("tags", TagCache.get());
        model.addAttribute("title", title);
        model.addAttribute("desc", desc);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", TagCache.get());
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


        String invalid = TagCache.filterInvalid(tag);
        if (StringUtils.isNotBlank(invalid)) {
            model.addAttribute("error", "输入非法标签:" + invalid);
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
