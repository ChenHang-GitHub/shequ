package com.csh.community.dto;

import lombok.Data;

@Data
public class commentInfo {
    private  Long id;
    private  Long parentId;
    private  int  type;
    private  int  commentator;
    private  Long gmtCreate ;
    private  Long gmtModify;
    private  Long likeCount;
}
