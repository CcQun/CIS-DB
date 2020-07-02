package com.example.demo.core.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class VolunteerRequest {
    @JsonProperty("id")
    private Integer id;

    private String name;

    private String gender;

    private String phone;

    private String id_card;

    private String birthday;

    private String checkin_date;

    @JsonProperty("DESCRIPTION")
    private String DESCRIPTION;

    @JsonProperty("ISACTIVE")
    private String ISACTIVE;

    @JsonProperty("CREATEBY")
    private Integer CREATEBY;

    @JsonProperty("UPDATEBY")
    private Integer UPDATEBY;

}
