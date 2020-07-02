package com.example.demo.controller;

import com.example.demo.core.Utils;
import com.example.demo.core.response.BaseResponse;
import com.example.demo.core.response.StatResponse;
import com.example.demo.db.model.EmployeeInfo;
import com.example.demo.db.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author CcQun
 * @Date 2020/6/30 17:04
 */

@RestController()
@RequestMapping("/employeeInfo")
public class EmployeeInfoController {
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

    public EmployeeInfoController(EmployeeInfoService employeeInfoService,
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

    //员工信息统计
    @RequestMapping("/statEmployee")
    public StatResponse statEmployee() throws Exception {
        StatResponse response = new StatResponse();
        List<EmployeeInfo> list =  employeeInfoService.findAll();
        int female=0;
        int male=0;
        int level1=0;
        int level2=0;
        int level3=0;
        int total=list.size();
        if(total<=0){
            response.setMsg("数据库中无工作人员信息");
            response.setCode(0);
            return response;
        }else{
            for(int i=0;i<total;i++){
                EmployeeInfo employeeInfo = list.get(i);
                int age = Utils.getAge(employeeInfo.getBirthday());
                System.out.println(employeeInfo.getUsername()+"_age:"+age);
                //统计年龄分布
                if(age<30){
                    level1++;
                }else if(age>=30&&age<40){
                    level2++;
                }else{
                    level3++;
                }

                //统计性别
                if(employeeInfo.getGender().equals("男")){
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
