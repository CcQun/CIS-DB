package com.example.demo.core.request;

import lombok.Data;

/**
 *@Author wyy
 *@Date 2020/6/30 15:54
 */

@Data
public class LoginRequest {
//    private Integer ts;
    private String username;
    private String password;
}
