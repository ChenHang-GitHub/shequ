package com.csh.community.dto;

import com.csh.community.exception.CustomizeErrorCode;
import lombok.Data;

@Data
public class MessageDTO {
    private  int code;
    private  String message;


    public  static  MessageDTO errorof(int code,String message)
    {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setCode(code);
        messageDTO.setMessage(message);
        return messageDTO;
    }

    public static MessageDTO errorof(CustomizeErrorCode customizeErrorCode) {

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setCode(customizeErrorCode.getCode());
        messageDTO.setMessage(customizeErrorCode.getMessage());
        return messageDTO;
    }
}
