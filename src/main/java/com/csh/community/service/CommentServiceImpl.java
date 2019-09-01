package com.csh.community.service;

import com.csh.community.dao.CommentInfoMapper;
import com.csh.community.dao.NotificationMapper;
import com.csh.community.dao.PublishMapper;
import com.csh.community.dao.UserMapper;
import com.csh.community.dto.CommentInfoDTO_ToPage;
import com.csh.community.pojo.CommentInfo;
import com.csh.community.pojo.Notification;
import com.csh.community.pojo.Question;
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

    @Resource
    PublishMapper publishMapper;

    @Resource
    NotificationMapper notificationMapper;

    @Override
    public void insertCommentService(CommentInfo commentInfo, User user) {
//        throw new RuntimeException(c)



        if(commentInfo.getType()==2)
        {
            //通过parentid 找到 receicer
            CommentInfo CommentFroReceiver =commentInfoMapper.getCommentByParentId(commentInfo.getParentId());


            //回复了评论
            Notification notification = new Notification();
            notification.setGmtcreate(System.currentTimeMillis());
            notification.setType(2);
            notification.setOuterid(commentInfo.getParentId());
            notification.setReceiver(CommentFroReceiver.getCommentator());
            notification.setNotifier(commentInfo.getCommentator());
            //读或未读的状态    0 未读 1已读  ---  可以改成枚举
            notification.setStatus(0);
            notification.setNotifiername(user.getName());
            //回复的content
            CommentInfo commentInfo1 =  commentInfoMapper.getCommentByParentId(commentInfo.getParentId());
            notification.setOutertitle(commentInfo1.getContent());
            notificationMapper.insert(notification);

        }else {
            //回复了问题
            Question questionReceiver =publishMapper.getQuesById(commentInfo.getParentId());
            Notification notification = new Notification();
            notification.setGmtcreate(System.currentTimeMillis());
            notification.setType(1);
            notification.setOuterid(commentInfo.getParentId());
            notification.setReceiver(questionReceiver.getCreator());
            notification.setNotifier(commentInfo.getCommentator());
            //读或未读的状态    0 未读 1已读  ---  可以改成枚举
            notification.setStatus(0);
            notification.setNotifiername(user.getName());
            Question question = publishMapper.getQuesById(commentInfo.getParentId());
            notification.setOutertitle(question.getTitle());
            notificationMapper.insert(notification);
        }

        commentInfoMapper.insertComment(commentInfo);
    }

    @Override
    public List<CommentInfoDTO_ToPage> getCommentInfoDTOList(Integer id) {

        List<CommentInfo> commentInfos = commentInfoMapper.getCommentInfoById(id);
        List<CommentInfoDTO_ToPage> commentInfoDTO_toPages = new ArrayList<>();
        for (CommentInfo commentInfo:commentInfos
             ) {
            User u  = userMapper.findById(commentInfo.getCommentator());
            CommentInfoDTO_ToPage commentInfoDTO_toPage = new CommentInfoDTO_ToPage();
            BeanUtils.copyProperties(commentInfo,commentInfoDTO_toPage);
            commentInfoDTO_toPage.setUser(u);
            commentInfoDTO_toPages.add(commentInfoDTO_toPage);
        }
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

        return commentInfoDTO_toPages;
    }
}
