package com.example.demo.db.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @Date 2020/6/29 20:40
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "oldperson_info")
@DynamicInsert
@DynamicUpdate
public class OldpersonInfo {
    @Id
    private Integer ID;
    private Integer ORG_ID;
    private Integer CLIENT_ID;
    private String username;
    private String gender;
    private String phone;
    private String id_card;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkin_date;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkout_date;
    private String imgset_dir;
    private String profile_photo;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date CREATED;
    private Integer CREATEBY;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date UPDATED;
    private Integer UPDATEBY;
    private String REMOVE;
}
