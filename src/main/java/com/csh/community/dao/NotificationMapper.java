package com.csh.community.dao;

import com.csh.community.pojo.Notification;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotificationMapper {

    void insert(Notification notification);

    List<Notification> getByReceiver(Integer id);

    int countUnReadCount(Integer id);

    Notification getNotiById(Integer id);

    void updateNotiById(Notification notification);
}
