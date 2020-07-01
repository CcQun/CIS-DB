package com.example.demo.db.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
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
@DynamicInsert
@DynamicUpdate
public class EmployeeInfo {
    @Id
    private Integer id;
    private Integer ORG_ID;
    private Integer CLIENT_ID;
    private String username;
    private String gender;
    private String phone;
    private String id_card;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date birthday;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date hire_date;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date resign_date;
    private String imgset_dir;
    private String profile_photo;
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
