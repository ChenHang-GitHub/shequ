package com.csh.community.dto;


import com.csh.community.pojo.User;
import lombok.Data;

@Data
public class CommentInfoDTO_ToPage  {
    private int id;
    private Long parentId;
    private int type;
    private int commentator;
    private Long gmtCreate;
    private Long gmtModify;
    private Long likeCount;
    private String content;
    private  String commentcount;
    private User user;

}
