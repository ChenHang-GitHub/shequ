package com.csh.community.service;

import com.csh.community.dto.NotificationDTO;

import java.util.List;

public interface NotificationService {
    List<NotificationDTO> getByReceiverId(Integer id);
}
