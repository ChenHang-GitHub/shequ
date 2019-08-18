package com.csh.community.pojo;

import lombok.Data;

@Data
public class Question {
    private  Integer id;
    private  String title;
    private  String description;
    private  Long  gmt_create;
    private  Long  gmt_modified;
    private int creator;
    private int comment_count;
    private int view_count;
    private int like_count;
    private String tag;


}
