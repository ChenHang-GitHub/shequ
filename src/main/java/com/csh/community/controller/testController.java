package com.csh.community.controller;

import com.csh.community.dao.UserMapper;
import com.csh.community.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/*
GET：读取（Read）
POST：新建（Create）
PUT：更新（Update）
PATCH：更新（Update）
DELETE：删除（Delete）
* */
@Controller
public class testController {

    Logger logger =  LoggerFactory.getLogger(testController.class);


    @Resource
    UserMapper userMapper;

    @GetMapping (value = "/")
    public  String test (Model model,
                         HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    logger.info("查询Cookie携带token");
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    logger.info(user.getName() + user.getToken()+user.getAvatar_url());
                /*
通过cookie存储一个session_id，然后具体的数据则是保存在session中。如果用户已经登录，则服务器会
在cookie中保存一个session_id，下次再次请求的时候，会把该session_id携带上来，服务器根据session_id在session
 库中获取用户的session数据。就能知道该用户到底是谁，以及之前保存的一些状态信息
 */
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        logger.trace("首页");
        return  "index";
    }

}
