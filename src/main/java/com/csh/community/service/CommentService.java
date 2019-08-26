package com.csh.community.service;

import com.csh.community.dto.CommentInfoDTO_ToPage;
import com.csh.community.pojo.CommentInfo;
import com.csh.community.pojo.User;

import java.util.List;

public interface CommentService {
    void insertCommentService(CommentInfo commentInfo, User user);

    List<CommentInfoDTO_ToPage> getCommentInfoDTOList(Integer id);

    List<CommentInfoDTO_ToPage> getTypeTwoCommentList(int id, int type);
}
