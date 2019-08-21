package com.csh.community.dto;

import com.csh.community.pojo.User;
import lombok.Data;

@Data
public class QuestionDTO {

    private  Integer id;
    private  String title;
    private  String description;
    private  Long  gmtCreate;
    private  Long  gmtModified;
    private int creator;
    private int commentCount;
    private int viewCount;
    private int likeCount;
    private String tag;
    private User user;


}
