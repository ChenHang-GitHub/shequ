package com.csh.community.service;

import com.csh.community.dto.CommentInfoDTO_ToPage;
import com.csh.community.pojo.CommentInfo;

import java.util.List;

public interface CommentService {
    void insertCommentService(CommentInfo commentInfo);

    List<CommentInfoDTO_ToPage> getCommentInfoDTOList(Integer id);
}
