package com.example.demo.core.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EditSysUserRequest {
    @JsonProperty("UserName")
    private String UserName;

    @JsonProperty("NewUserName")
    private String NewUserName;

    @JsonProperty("REAL_NAME")
    private String REAL_NAME;

    @JsonProperty("SEX")
    private String SEX;

    @JsonProperty("EMAIL")
    private String EMAIL;

    @JsonProperty("PHONE")
    private String PHONE;

    @JsonProperty("MOBILE")
    private String MOBILE;

}
