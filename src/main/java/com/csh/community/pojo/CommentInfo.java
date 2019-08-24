package com.csh.community.pojo;

import lombok.Data;

@Data
public class CommentInfo {
    private  int id;
    private  Long parentId;
    private  int  type;
    private  int  commentator;
    private  Long gmtCreate ;
    private  Long gmtModify;
    private  Long likeCount;
    private  String content;
}
