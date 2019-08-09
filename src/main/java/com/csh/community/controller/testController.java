package com.csh.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class testController {


    @GetMapping (value = "/hello")
    public  String test ()
    {

        return  "/a";
    }

}
