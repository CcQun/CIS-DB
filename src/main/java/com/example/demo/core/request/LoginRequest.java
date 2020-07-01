package com.example.demo.core.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *@Author wyy
 *@Date 2020/6/30 15:54
 */

@Data
public class LoginRequest {
    @JsonProperty("UserName")
    private String UserName;
    @JsonProperty("Password")
    private String Password;
}
