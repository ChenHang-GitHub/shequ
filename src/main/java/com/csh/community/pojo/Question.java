package com.csh.community.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Question  implements Serializable {
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
}
