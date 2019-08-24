package com.csh.community.dto;

import lombok.Data;
/*
*  页面传递给controller的DTO
*
* */
@Data
public class CommentInfoDTO_FromPage {

    private  Long parentId;
    private  int  type;
    private  String content;

}

