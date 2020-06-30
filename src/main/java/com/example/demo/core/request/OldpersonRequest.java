package com.example.demo.core.request;
import lombok.Data;

import java.util.Date;

@Data
public class OldpersonRequest {
    private String username;

    private String gender;

    private String phone;

    private String id_card;

    private Date birthday;

    private Date checkin_date;

    private String room_number;

    private String firstguardian_name;

    private String firstguardian_relationship;

    private String firstguardian_phone;

    private String firstguardian_wechat;

    private String secondguardian_name;

    private String secondguardian_relationship;

    private String secondguardian_phone;

    private String secondguardian_wechat;

    private String health_state;

    private String DESCRIPTION;

    private String ISACTIVE;

    private Integer CREATEBY;

}
