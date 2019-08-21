package com.csh.community.dao;


import com.csh.community.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {



    void insertGitHubUser(User u);

    User findByToken(String token);


    User findById(int creator);

    User findByAccountId(Integer id);

    void updateUser(User u);
}
