package com.example.demo.controller;

import com.example.demo.core.Utils;
import com.example.demo.core.request.OldpersonRequest;
import com.example.demo.core.response.BaseResponse;
import com.example.demo.core.response.ListResponse;
import com.example.demo.core.response.OldStatResponse;
import com.example.demo.db.model.OldpersonInfo;
import com.example.demo.db.service.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public BaseResponse addOldPerson(@RequestBody OldpersonRequest request) throws ParseException {

        Date day = new Date();
        System.out.println("加入老人");
        System.out.println("创建时间"+day.toString());
        OldpersonInfo oldperson= OldpersonInfo.builder()
                .ID(getIDNumber()+1)
                .username(request.getUsername())
                .gender(request.getGender())
                .phone(request.getPhone())
                .id_card(request.getId_card())
                .birthday(Utils.strToDateLong(request.getBirthday()))
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
                .build();
        System.out.println("开始插表");
        BaseResponse reponse=new BaseResponse();
        List<OldpersonInfo> oldpersons = oldpersonInfoService.findAll();
        if(oldpersons.size()>0){
            reponse.setCode(0);
            reponse.setMsg("add one oldperson failed, this old person already exists!!");
            System.out.println("加入失败");
            return reponse;
        }
        oldperson.setCheckin_date(Utils.strToDateLong(request.getCheckin_date()));
        oldperson.setCREATED(day);
        System.out.println(request.getCREATEBY());
        System.out.println(request.getDESCRIPTION());
        System.out.println(request.getISACTIVE());
        oldperson.setCREATEBY(request.getCREATEBY());
        oldpersonInfoService.save(oldperson);
        System.out.println(request.getCREATEBY()+"加入成功");
        reponse.setCode(1);
        reponse.setMsg("add one oldperson successfully!!");
        return reponse;
    }

    //修改老人信息
    @RequestMapping("/editOldPerson")
    public BaseResponse editOldPerson(@RequestBody OldpersonRequest request) {

        Date day=new Date();
        OldpersonInfo oldperson= OldpersonInfo.builder()
                .ID(request.getID())
                .gender(request.getGender())
                .phone(request.getPhone())
                .id_card(request.getId_card())
                .birthday(Utils.strToDateLong(request.getBirthday()))
                .checkin_date(Utils.strToDateLong(request.getCheckin_date()))
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
                .UPDATED(day)
                .UPDATEBY(request.getCREATEBY())
                .build();
        oldpersonInfoService.save(oldperson);

        BaseResponse reponse=new BaseResponse();
        reponse.setCode(1);
        reponse.setMsg("edit oldperson successfully!!");
        return reponse;
    }

    //获得最大id
    public Integer getIDNumber() {
        List<OldpersonInfo> oldpersons = oldpersonInfoService.findAll();
        Integer idNumber = 0;
        for (int i = 0; i < oldpersons.size(); i++) {
            if (oldpersons.get(i).getID() > idNumber) {
                idNumber = oldpersons.get(i).getID();
            }
        }
        return idNumber;
    }

    @RequestMapping("/statOlePerson")//统计老人年龄分布，性别比例
    public OldStatResponse statOlePerson() throws Exception {
        OldStatResponse response = new OldStatResponse();
        List<OldpersonInfo> list = oldpersonInfoService.findAll();
        int female=0;
        int male=0;
        int level1=0;
        int level2=0;
        int level3=0;
        int total=list.size();
        if(total==0){
            response.setMsg("数据库中无老人信息");
            response.setCode(0);
            return response;
        }else{
            for(int i=0;i<total;i++){
                OldpersonInfo oldpersonInfo = list.get(i);
                int age= Utils.getAge(oldpersonInfo.getBirthday());
                if(age<60){
                    level1++;
                }else if(age>=60&&age<70){
                    level2++;
                }
                if(oldpersonInfo.getGender().equals("男")){
                    male++;
                }else{
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
