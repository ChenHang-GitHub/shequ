package com.csh.community.dao;

import com.csh.community.pojo.Employee;

import com.csh.community.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {


    Employee getEmpById(Integer id);

    void insertGitHubUser(User u);


}
