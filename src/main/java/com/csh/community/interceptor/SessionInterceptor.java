package com.csh.community.interceptor;

import com.csh.community.dao.UserMapper;
import com.csh.community.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//添加service 注解让spring 接管这个类
@Service
public class SessionInterceptor implements HandlerInterceptor {


    @Resource
    UserMapper userMapper ;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("进入pre拦截器");
        Cookie[] cookies = request.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    System.out.println(token+"??????????");
                    User user = userMapper.findByToken(token);
//                    System.out.println(user.toString());
//                    logger.info(user.getName() + user.getToken()+user.getAvatarUrl());
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
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
