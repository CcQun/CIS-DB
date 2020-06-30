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
 * @Date 2020/6/29 20:51
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "volunteer_info")
@DynamicInsert
@DynamicUpdate
public class VolunteerInfo {
    @Id
    private Integer id;
    private Integer ORG_ID;
    private Integer CLIENT_ID;
    private String name;
    private String gender;
    private String phone;
    private String id_card;
    private Date birthday;
    private Date checkin_date;
    private Date checkout_date;
    private String imgset_dir;
    private String profile_photo;
    private String DESCRIPTION;
    private String ISACTIVE;
    private Date CREATED;
    private Integer CREATEBY;
    private Date UPDATED;
    private Integer UPDATEBY;
    private String REMOVE;
}