package com.csh.community.service;

import com.csh.community.dao.CommentInfoMapper;
import com.csh.community.dao.UserMapper;
import com.csh.community.dto.CommentInfoDTO_ToPage;
import com.csh.community.pojo.CommentInfo;
import com.csh.community.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements  CommentService {

    @Resource
    CommentInfoMapper commentInfoMapper;


    @Resource
    UserMapper userMapper;

    @Override
    public void insertCommentService(CommentInfo commentInfo) {
//        throw new RuntimeException(c)


        commentInfoMapper.insertComment(commentInfo);

    }

    @Override
    public List<CommentInfoDTO_ToPage> getCommentInfoDTOList(Integer id) {

        List<CommentInfo> commentInfos = commentInfoMapper.getCommentInfoById(id);
        System.out.println(commentInfos.toString() +"test commentinfos");
        List<CommentInfoDTO_ToPage> commentInfoDTO_toPages = new ArrayList<>();
        for (CommentInfo commentInfo:commentInfos
             ) {
            User u  = userMapper.findById(commentInfo.getCommentator());
            CommentInfoDTO_ToPage commentInfoDTO_toPage = new CommentInfoDTO_ToPage();
            BeanUtils.copyProperties(commentInfo,commentInfoDTO_toPage);
            commentInfoDTO_toPage.setUser(u);
            commentInfoDTO_toPages.add(commentInfoDTO_toPage);
        }
        System.out.println(commentInfoDTO_toPages.toString());
        return commentInfoDTO_toPages;
    }

    @Override
    public List<CommentInfoDTO_ToPage> getTypeTwoCommentList(int id, int type) {
        List<CommentInfo> commentInfoList = commentInfoMapper.getCommentByIdAndType(id,type);
        List<CommentInfoDTO_ToPage> commentInfoDTO_toPages = new ArrayList<>();
        for (CommentInfo commentInfo:commentInfoList
             ) {
            User u  =  userMapper.findById(commentInfo.getCommentator());
            CommentInfoDTO_ToPage commentInfoDTO_toPage = new CommentInfoDTO_ToPage();
            BeanUtils.copyProperties(commentInfo,commentInfoDTO_toPage);
            commentInfoDTO_toPage.setUser(u);
            commentInfoDTO_toPages.add(commentInfoDTO_toPage);
        }

        System.out.println("test 2ji ::" +commentInfoDTO_toPages.toString());
        return commentInfoDTO_toPages;
    }
}
