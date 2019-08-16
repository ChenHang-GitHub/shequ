package com.csh.community.controller;

import com.csh.community.dao.UserMapper;
import com.csh.community.pojo.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


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
    public  String test (Model model)
    {
        logger.trace("首页");
        return  "index";
    }



    @GetMapping("/emp/{id}")
    public String getEmp(@PathVariable("id") Integer id) {
        logger.info("here///");
        Employee empById = userMapper.getEmpById(id);

        System.out.println(empById);
        return "index";
    }
}
