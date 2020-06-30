package com.example.demo.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private Integer ID;
    private Integer ORG_ID;
    private Integer CLIENT_ID;
    private String UserName;
    private String Password;
    private String REAL_NAME;
    private String SEX;
    private String EMAIL;
    private String PHONE;
    private String MOBILE;
    private String DESCRIPTION;
    private String ISACTIVE;
    private Date CREATED;
    private Integer CREATEBY;
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
