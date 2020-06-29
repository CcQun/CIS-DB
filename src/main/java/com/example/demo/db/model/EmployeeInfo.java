package com.example.demo.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author CcQun
 * @Date 2020/6/29 20:48
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "employee_info")
public class EmployeeInfo {
    @Id
    private Integer id;
    private Integer ORG_ID;
    private Integer CLIENT_ID;
    private String username;
    private String gender;
    private String phone;
    private String id_card;
    private Date birthday;
    private Date hire_date;
    private Date resign_date;
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
