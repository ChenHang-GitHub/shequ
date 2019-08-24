package com.csh.community.dao;


import com.csh.community.pojo.CommentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentInfoMapper {
    void insertComment(CommentInfo commentInfo);

    List<CommentInfo> getCommentInfoById(Integer id);
}
