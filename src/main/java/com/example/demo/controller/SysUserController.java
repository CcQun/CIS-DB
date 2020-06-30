package com.example.demo.controller;

import com.example.demo.core.request.LoginRequest;
import com.example.demo.core.response.BaseResponse;
import com.example.demo.db.model.SysUser;
import com.example.demo.db.service.*;
//import com.example.demo.core.request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//        String username = SysUserService.findAll(request.getUsername()).getUserName();
        SysUser user = SysUser.builder().UserName(request.getUsername()).build();

        if(request.getUsername()==""){
            response.setCode(0);
            response.setMsg("用户名不能为空！");
            return response;
        }
        else if(request.getPassword()==""){
            response.setCode(0);
            response.setMsg("密码不能为空！");
            return response;
        }
        else if(request.getUsername().equals(user.getUserName())&&request.getPassword().equals(user.getPassword())){
            response.setCode(1);
            response.setMsg(request.getUsername()+"登录成功");
            return response;
        }
        else {
            response.setCode(0);
            response.setMsg("用户名或密码错误");
            return response;
        }
    }
}
