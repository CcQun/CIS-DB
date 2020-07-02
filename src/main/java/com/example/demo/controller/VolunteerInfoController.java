package com.example.demo.controller;

import com.example.demo.core.Utils;
import com.example.demo.core.response.StatResponse;
import com.example.demo.db.model.VolunteerInfo;
import com.example.demo.db.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author CcQun
 * @Date 2020/6/30 17:07
 */
@RestController()
@RequestMapping("/volunteerInfo")
public class VolunteerInfoController {
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

    public VolunteerInfoController(EmployeeInfoService employeeInfoService,
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

    @RequestMapping("/statVolunteer")
    public StatResponse statVolunteer() throws Exception {
        StatResponse response = new StatResponse();
        List<VolunteerInfo> list = volunteerInfoService.findAll();
        int female=0;
        int male=0;
        int level1=0;
        int level2=0;
        int level3=0;
        int total=list.size();
        if(total<=0){
            response.setMsg("数据库中无义工信息");
            response.setCode(0);
            return response;
        }else{
            for(int i=0;i<total;i++){
                VolunteerInfo volunteerInfo = list.get(i);
                int age= Utils.getAge(volunteerInfo.getBirthday());
                System.out.println(volunteerInfo.getName()+"_age:"+age);

                //年龄统计
                if(age<40){
                    level1++;
                }else if(age>=40&&age<50){
                    level2++;
                }else {
                    level3++;
                }
                //统计性别
                if (volunteerInfo.getGender().equals("男")){
                    male++;
                }else {
                    female++;
                }
            }
        }

        response.setTotalNumber(total);
        response.setNumberOfFe(female);
        response.setNumberOfMa(male);
        response.setNumberOfL1(level1);
        response.setNumberOfL2(level2);
        response.setNumberOfL3(level3);
        response.setCode(1);
        response.setMsg("统计信息返回");
        return response;
    }
}
