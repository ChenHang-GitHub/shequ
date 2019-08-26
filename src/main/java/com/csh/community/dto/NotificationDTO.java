package com.csh.community.dto;

import lombok.Data;

@Data
public class NotificationDTO {
    private int id;
    private int notifier;
    private Long outerid;
    private Long gmtcreate;
    private int status;
    private String notifiername;
    private String outertitle;
//need to add
    private String  typeName;
    private Integer type;


}
