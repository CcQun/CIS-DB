package com.example.demo.controller;

import com.example.demo.core.Utils;
import com.example.demo.core.request.EventRequest;
import com.example.demo.core.request.OldpersonRequest;
import com.example.demo.core.response.BaseResponse;
import com.example.demo.db.model.EventInfo;
import com.example.demo.db.model.OldpersonInfo;
import com.example.demo.db.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

/**
 * @Author CcQun
 * @Date 2020/6/30 17:04
 */
@RestController()
@RequestMapping("/eventInfo")
public class EventInfoController {
    @Autowired
    private final EmployeeInfoService employeeInfoService;
    @Autowired
    private final EventInfoService eventInfoService;
    @Autowired
    private final OldpersonInfoService oldpersonInfoService;
    @Autowired
    private final SysUserService sysUserService;
    @Autowired
    private final VolunteerInfoService volunteerInfoService;

    public EventInfoController(EmployeeInfoService employeeInfoService,
                               EventInfoService eventInfoService,
                               OldpersonInfoService oldpersonInfoService,
                               SysUserService sysUserService,
                               VolunteerInfoService volunteerInfoService) {
        this.employeeInfoService = employeeInfoService;
        this.eventInfoService = eventInfoService;
        this.oldpersonInfoService = oldpersonInfoService;
        this.sysUserService = sysUserService;
        this.volunteerInfoService = volunteerInfoService;
    }

    //插入事件
    @RequestMapping("/addEvent")
    public BaseResponse addEvent(@RequestBody EventRequest request) throws ParseException {
        BaseResponse response = new BaseResponse();
        EventInfo event = EventInfo.builder()
                .id(getIDNumber() + 1)
                .event_desc(request.getEvent_desc())
                .event_type(request.getEvent_type())
                .event_date(Utils.strToDateLong(request.getEvent_date()))
                .event_location(request.getEvent_location())
                .oldperson_id(request.getOldperson_id())
                .build();
        eventInfoService.save(event);
        response.setCode(1);
        response.setMsg((getIDNumber() + 1) + "");
        return response;
    }


    //获得最大id
    public Integer getIDNumber() {
        List<EventInfo> events = eventInfoService.findAll();
        Integer idNumber = 0;
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId() > idNumber) {
                idNumber = events.get(i).getId();
            }
        }
        return idNumber;
    }

}
