package com.example.demo.core.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChangePassRequest {
    @JsonProperty("UserName")
    private String UserName;

    @JsonProperty("Password")
    private String Password;

    @JsonProperty("NewPassword")
    private String NewPassword;
}
