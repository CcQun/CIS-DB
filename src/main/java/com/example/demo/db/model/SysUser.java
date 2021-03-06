package com.example.demo.db.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author CcQun
 * @Date 2020/6/29 20:57
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "sys_user")
@DynamicInsert
@DynamicUpdate
public class SysUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private Integer ORG_ID;
    private Integer CLIENT_ID;

    @Column(name = "username")
    private String UserName;

    private String Password;
    private String REAL_NAME;
    private String SEX;
    private String EMAIL;
    private String PHONE;
    private String MOBILE;
    private String DESCRIPTION;
    private String ISACTIVE;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date CREATED;
    private Integer CREATEBY;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date UPDATED;
    private Integer UPDATEBY;
    private String REMOVE;
    private String DATAFILTER;
    private String theme;
    private String defaultpage;
    private String logoimage;
    private String qqopenid;
    private String appversion;
    private String jsonauth;
}
