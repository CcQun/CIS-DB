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
 * @Date 2020/6/29 20:54
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "event_info")
@DynamicInsert
@DynamicUpdate
public class EventInfo {
    @Id
    private Integer id;
    private Integer event_type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date event_date;
    private String event_location;
    private String event_desc;
    private Integer oldperson_id;
}
