package com.example.demo.controller;

import com.example.demo.core.Utils;
import com.example.demo.core.request.LoginRequest;
import com.example.demo.core.response.BaseResponse;
import com.example.demo.db.model.SysUser;
import com.example.demo.db.service.*;
//import com.example.demo.core.request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.demo.db.service.SysUserService.*;

/**
 * @Author CcQun
 * @Date 2020/6/30 17:07
 */
@RestController()
@RequestMapping("/sysUser")
public class SysUserController {
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

    public SysUserController(EmployeeInfoService employeeInfoService,
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

    @RequestMapping("/login")
    public BaseResponse login(@RequestBody LoginRequest request){
        BaseResponse response = new BaseResponse();
        String MD5Password = Utils.getMD5(request.getPassword());
        SysUser user = SysUser.builder().UserName(request.getUserName()).build();
        List<SysUser> list = sysUserService.findAll(user);

        if(list.size() > 0){
            if(request.getUserName().equals(list.get(0).getUserName())&& MD5Password.equals(list.get(0).getPassword())){
                response.setCode(1);
                response.setMsg(request.getUserName());
            }
            else {
                response.setCode(0);
                response.setMsg("用户名或密码错误");
            }
        }
        else {
            response.setCode(0);
            response.setMsg("用户名"+request.getUserName()+"不存在");
        }
        return response;
    }
}
