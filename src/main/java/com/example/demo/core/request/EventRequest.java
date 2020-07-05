package com.example.demo.core.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class EventRequest {
    @JsonProperty("event_desc")
    private String event_desc;

    @JsonProperty("event_type")
    private Integer event_type;

    @JsonProperty("event_date")
    private String event_date;

    @JsonProperty("event_location")
    private String event_location;

    @JsonProperty("oldperson_id")
    private Integer oldperson_id;
}
