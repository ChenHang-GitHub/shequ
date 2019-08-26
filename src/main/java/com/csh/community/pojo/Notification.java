package com.csh.community.pojo;

import lombok.Data;

@Data
public class Notification {
    private  int id;
    private  int notifier;
    private  int receiver;
    private  Long outerid;
    private  int type;
    private  Long gmtcreate;
    private  int status;
    private  String notifiername;
    private  String outertitle;

}
