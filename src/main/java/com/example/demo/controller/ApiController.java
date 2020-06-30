package com.example.demo.controller;

import com.example.demo.db.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author CcQun、FyLe、ZsMeng、WyYing
 * @Date 2020/6/29 17:05
 */
@RestController()
@RequestMapping("/api")
public class ApiController {
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

    public ApiController(EmployeeInfoService employeeInfoService,
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
        SysUser user = SysUserService.findAll(request.getUsername());

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
        else if(request.getUsername().equals(user.getUsername())&&request.getPassword().equals(user.getPassword())){
            response.setCode(1);
            response.setMsg("登录成功");
            return response;
        }
        else {
            response.setCode(0);
            response.setMsg("用户名或密码错误");
            return response;
        }
    }
}
