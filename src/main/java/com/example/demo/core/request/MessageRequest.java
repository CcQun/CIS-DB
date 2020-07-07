package com.example.demo.core.request;

import lombok.Data;

@Data
public class MessageRequest {
    private String userId;//管理员id
    private String id;//被采集人员id
    private String message;
    private String type;//oldpeople employee volunteer
}
