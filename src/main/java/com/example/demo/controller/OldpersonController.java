package com.example.demo.controller;

import com.example.demo.core.request.OldpersonRequest;
import com.example.demo.core.response.BaseResponse;
import com.example.demo.core.response.ListResponse;
import com.example.demo.db.model.OldpersonInfo;
import com.example.demo.db.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Author CcQun
 * @Date 2020/6/30 17:05
 */
@RestController()
@RequestMapping("/oldperson")
public class OldpersonController {
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

    public OldpersonController(EmployeeInfoService employeeInfoService,
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

    //录入老人信息/新增
    @RequestMapping("/addOldPerson")
    public BaseResponse selectStudent(@RequestBody OldpersonRequest request) {

        Date day=new Date();
        OldpersonInfo oldperson= OldpersonInfo.builder()
                .username(request.getUsername())
                .gender(request.getGender())
                .phone(request.getPhone())
                .id_card(request.getId_card())
                .birthday(request.getBirthday())
                .checkin_date(request.getCheckin_date())
                .room_number(request.getRoom_number())
                .firstguardian_name(request.getFirstguardian_name())
                .firstguardian_relationship(request.getFirstguardian_relationship())
                .firstguardian_phone(request.getFirstguardian_phone())
                .firstguardian_wechat(request.getFirstguardian_wechat())
                .secondguardian_name(request.getSecondguardian_name())
                .secondguardian_relationship(request.getSecondguardian_relationship())
                .secondguardian_phone(request.getSecondguardian_phone())
                .secondguardian_wechat(request.getSecondguardian_wechat())
                .health_state(request.getHealth_state())
                .DESCRIPTION(request.getDESCRIPTION())
                .ISACTIVE(request.getISACTIVE())
                .CREATED(day)
                .CREATEBY(request.getCREATEBY())
                .build();

        BaseResponse reponse=new BaseResponse();
        reponse.setCode(1);
        reponse.setMsg("add one oldperson successfully!!");
        return reponse;
    }

}
