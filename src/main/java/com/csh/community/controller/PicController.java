package com.csh.community.controller;


import com.csh.community.dto.PicDTO;
import com.csh.community.provider.OSSClientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.Date;

@Controller
public class PicController {


    @Resource
    OSSClientUtil ossClientUtil;

    @RequestMapping(value = "/pic/upload")
    @ResponseBody
    public PicDTO upload(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartHttpServletRequest  = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile =multipartHttpServletRequest.getFile("editormd-image-file");
        String name=ossClientUtil.uploadImg2Oss(multipartFile);
        String imgUrl = ossClientUtil.getImgUrl(name);

        System.out.println("测试URL和Name"+imgUrl +"\n"+name);

        PicDTO picDTO = new PicDTO();
        picDTO.setSuccess(1);
        picDTO.setUrl(imgUrl);
        return  picDTO;
    }

//    public  String getUrl(String key) {
//        // 设置URL过期时间为10年  3600l* 1000*24*365*10
//        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
//        // 生成URL
//        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
//        if (url != null) {
//            return url.toString();
//        }
//        return null;
//    }

}
