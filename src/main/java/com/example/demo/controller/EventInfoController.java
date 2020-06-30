package com.example.demo.controller;

import com.example.demo.db.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
