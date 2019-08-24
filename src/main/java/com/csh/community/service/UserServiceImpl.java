package com.csh.community.service;

import com.csh.community.controller.PersonalController;
import com.csh.community.dao.UserMapper;
import com.csh.community.pojo.User;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public void insertOrUpdateUser(User u) {
        User user =  userMapper.findByAccountId(Integer.valueOf(u.getAccountId()));
        if(user==null)
        {
            //insert
            System.out.println("user is null"+Integer.valueOf(u.getAccountId())+u.toString());
            userMapper.insertGitHubUser(u);
        }else
        {
            System.out.println("user update"+u.toString());
//            update  替换可能会变换的值
            user.setName(u.getName());
            user.setToken(u.getToken());
            user.setAvatarUrl(u.getAvatarUrl());
            userMapper.updateUser(user);

        }

    }
}
