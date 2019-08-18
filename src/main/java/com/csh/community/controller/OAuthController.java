package com.csh.community.controller;


import com.csh.community.dao.UserMapper;
import com.csh.community.dto.AccessTokenDTO;
import com.csh.community.pojo.User;
import com.csh.community.provider.GitHubPro;
import com.csh.community.provider.GitHubUser;
import org.apache.ibatis.annotations.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class OAuthController {

    @Autowired
    GitHubPro gitHubPro;

    @Value("${github.client.id}")
            private String cid;
    @Value("${github.client.secert}")
            private String csecert;
    @Value("${github.client.uri}")
            private String curi;

    @Resource
    UserMapper userMapper;

    Logger logger =  LoggerFactory.getLogger(testController.class);
    @GetMapping(value = "callback")
    public String callBack(@RequestParam(name="code") String code,
                           @RequestParam(name = "state")String state,
                           HttpServletRequest request,
                           HttpServletResponse response) throws Exception {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(curi);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(cid);
        accessTokenDTO.setClient_secret(csecert);

        String accessToken = gitHubPro.getAccessToken(accessTokenDTO);
        GitHubUser user = gitHubPro.getGitHubUser(accessToken);
        logger.info(user.getName()+user.getBio()+user.getId()+user.getAvatar_url()+"????????");
        logger.info("登入后返回index");
        if(user!=null)
        {
//            request.getSession().setAttribute("user",user);
            logger.info(user.toString()+"???");
            User u= new User();
            //通过序列自增
            u.setId(1);
            u.setName(user.getName());
            u.setAccount_Id(user.getId().toString());
            u.setAvatar_url(user.getAvatar_url());
            //自己创建token
            String token  = UUID.randomUUID().toString();
            u.setToken(token);
            userMapper.insertGitHubUser(u);
            response.addCookie(new Cookie("token",token));
            return  "redirect:/";
        }else
        {
            return  "redirect:/";
        }
    }


}
