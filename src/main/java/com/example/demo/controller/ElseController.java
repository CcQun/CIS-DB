package com.example.demo.controller;

import com.example.demo.core.cm.PeopleInfo;
import com.example.demo.core.request.LoginRequest;
import com.example.demo.core.request.MessageRequest;
import com.example.demo.core.request.OldpersonRequest;
import com.example.demo.core.response.BaseResponse;
import com.example.demo.core.response.DataResponse;
import com.example.demo.core.response.ListResponse;
import com.example.demo.core.socket.WebSocketServer;
import com.example.demo.db.model.EmployeeInfo;
import com.example.demo.db.model.OldpersonInfo;
import com.example.demo.db.model.SysUser;
import com.example.demo.db.model.VolunteerInfo;
import com.example.demo.db.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author CcQun、FyLe、ZsMeng、WyYing
 * @Date 2020/6/29 17:05
 */
@RestController()
@RequestMapping("/else")
public class ElseController {
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

    public ElseController(EmployeeInfoService employeeInfoService,
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

    //获取所有人id_card 和名字和类型
    @RequestMapping("/queryAll")
    public ListResponse queryAll() throws ParseException {
        ListResponse response=new ListResponse();
        List<PeopleInfo> list=new ArrayList<PeopleInfo>();

        List<OldpersonInfo> old_list=oldpersonInfoService.findAll();
        for(int i=0;i<old_list.size();i++){
            if(old_list.get(i).getREMOVE().equals("0")){
                PeopleInfo p=new PeopleInfo();
                p.setId_card(old_list.get(i).getID());
                p.setName(old_list.get(i).getUsername());
                p.setType("old_people");
                list.add(p);
            }
        }

        List<EmployeeInfo> employee_list=employeeInfoService.findAll();
        for(int i=0;i<employee_list.size();i++){
            if(employee_list.get(i).getREMOVE().equals("0")){
                PeopleInfo p=new PeopleInfo();
                p.setId_card(employee_list.get(i).getId());
                p.setName(employee_list.get(i).getUsername());
                p.setType("employee");
                list.add(p);
            }
        }

        List<VolunteerInfo> volunteer_list=volunteerInfoService.findAll();
        for(int i=0;i<volunteer_list.size();i++){
            if(volunteer_list.get(i).getREMOVE().equals("0")){
                PeopleInfo p=new PeopleInfo();
                p.setId_card(volunteer_list.get(i).getId());
                p.setName(volunteer_list.get(i).getName());
                p.setType("volunteer");
                list.add(p);
            }
        }
        response.setCode(1);
        response.setMsg("query successfully!!");
        response.setData(list);
        return response;

    }


    @RequestMapping("/cffeedback")
    public BaseResponse cffeedback(@RequestBody MessageRequest request) throws IOException {
        if(request.getMessage().equals("采集完成")){
            if(request.getType().equals("oldpeople")) {
                OldpersonInfo old = oldpersonInfoService.findOldpersonByID(Integer.parseInt(request.getId()));
                old.setISACTIVE("已采集");
                oldpersonInfoService.save(old);
            }
            else if(request.getType().equals("employee")){
                EmployeeInfo employee=employeeInfoService.findEmployeeByID(Integer.parseInt(request.getId()));
                employee.setISACTIVE("已采集");
                employeeInfoService.save(employee);
            }
            else{
                VolunteerInfo v=volunteerInfoService.findVolunteerByID(Integer.parseInt(request.getId()));
                v.setISACTIVE("已采集");
                volunteerInfoService.save(v);
            }


        }

        WebSocketServer.sendInfo(request.getMessage(),request.getUserId());
        BaseResponse response=new BaseResponse();
        response.setCode(1);
        response.setMsg("已传送");
        return response;

    }
}
