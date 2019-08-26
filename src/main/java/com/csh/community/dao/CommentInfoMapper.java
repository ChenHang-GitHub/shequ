package com.csh.community.dao;


import com.csh.community.dto.CommentInfoDTO_ToPage;
import com.csh.community.pojo.CommentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentInfoMapper {
    void insertComment(CommentInfo commentInfo);

    List<CommentInfo> getCommentInfoById(Integer id);

    List<CommentInfo> getCommentByIdAndType(@Param("id") int id,@Param("type") int type);

    CommentInfo getCommentByParentId(Long parentId);

    CommentInfo getCommentInfoByNotiId(Long outerid);
}
