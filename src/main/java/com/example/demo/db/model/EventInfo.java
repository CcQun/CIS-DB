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
 * @Date 2020/6/29 20:54
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "event_info")
public class EventInfo {
    @Id
    private Integer id;
    private Integer event_type;
    private Date event_date;
    private String event_location;
    private String event_desc;
    private Integer oldperson_id;
}
