package com.example.demo.core.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class OldpersonRequest {
    @JsonProperty("ID")
    private Integer ID;

    private String username;

    private String gender;

    private String phone;

    private String id_card;

    private String birthday;

    private String checkin_date;

    private Date checkout_date;

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
    @JsonProperty("DESCRIPTION")
    private String DESCRIPTION;
    @JsonProperty("ISACTIVE")
    private String ISACTIVE;
    @JsonProperty("CREATEBY")
    private Integer CREATEBY;
    @JsonProperty("UPDATEBY")
    private Integer UPDATEBY;

}
