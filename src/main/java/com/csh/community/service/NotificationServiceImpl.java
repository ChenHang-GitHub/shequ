package com.csh.community.service;

import com.csh.community.dao.NotificationMapper;
import com.csh.community.dto.NotificationDTO;
import com.csh.community.pojo.Notification;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl  implements  NotificationService{


    @Resource
    NotificationMapper notificationMapper;

    @Override
    public List<NotificationDTO> getByReceiverId(Integer id) {
        List<Notification>  notificationList =   notificationMapper.getByReceiver(id);
        List<NotificationDTO> notificationDTOList =new ArrayList<>();
        for (Notification notification: notificationList
             ) {
            NotificationDTO notificationDTO =new NotificationDTO();
            BeanUtils.copyProperties(notification,notificationDTO);
            if(notification.getType()==2) {
                notificationDTO.setTypeName("回复了您的评论");
            }else {
                notificationDTO.setTypeName("回复了您的问题");

            }

            notificationDTOList.add(notificationDTO);
        }

        return notificationDTOList;
    }
}
