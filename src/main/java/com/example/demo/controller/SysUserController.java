package com.example.demo.controller;

import com.example.demo.core.Utils;
import com.example.demo.core.request.ChangePassRequest;
import com.example.demo.core.request.EditSysUserRequest;
import com.example.demo.core.request.LoginRequest;
import com.example.demo.core.request.RegisterRequest;
import com.example.demo.core.response.BaseResponse;
import com.example.demo.core.response.DataResponse;
import com.example.demo.core.response.ListResponse;
import com.example.demo.db.model.SysUser;
import com.example.demo.db.service.*;
//import com.example.demo.core.request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public ListResponse login(@RequestBody LoginRequest request){
        ListResponse response = new ListResponse();
        List returnList = new ArrayList();
        String MD5Password = Utils.getMD5(request.getPassword());
        SysUser user = SysUser.builder().UserName(request.getUserName()).Password(MD5Password).build();
        List<SysUser> list = sysUserService.findAll(user);

        if(list.size() > 0){
            if(request.getUserName().equals(list.get(0).getUserName())&& MD5Password.equals(list.get(0).getPassword())){
                response.setCode(1);
                response.setMsg(request.getUserName());
                returnList.add(list.get(0).getID());
                response.setData(returnList);
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

    @RequestMapping("/register")
    public BaseResponse register(@RequestBody RegisterRequest request){
        BaseResponse response = new BaseResponse();
//        System.out.println(request.toString());
        String MD5Password = Utils.getMD5(request.getPassword());
//        System.out.println(MD5Password+"-111");

        SysUser user = SysUser.builder()
                .UserName(request.getUserName())
                .REAL_NAME(request.getREAL_NAME())
                .SEX(request.getSEX())
                .EMAIL(request.getEMAIL())
                .PHONE(request.getPHONE())
                .MOBILE(request.getMOBILE())
                .Password(MD5Password)
                .build();
//        System.out.println(user.getPassword()+"---------");
        List<SysUser> list = sysUserService.findAllByUserName(request.getUserName());

        if(list.size() <= 0){
            sysUserService.getMapper().save(user);
            response.setCode(1);
            response.setMsg(request.getUserName());
        }
        else {
            response.setCode(0);
            response.setMsg("用户"+request.getUserName()+"已存在");
        }
        return response;
    }

    @RequestMapping("/seeSysUser")
    public ListResponse<SysUser> seeSysUser(@RequestBody EditSysUserRequest request){
        ListResponse<SysUser> response = new ListResponse<>();
        List<SysUser> list = sysUserService.findAllByUserName(request.getUserName());

        response.setData(list);
        response.setCode(1);
        response.setMsg(request.getUserName());

        return response;
    }

    @RequestMapping("/editSysUser")
    public BaseResponse edit(@RequestBody EditSysUserRequest request){
        BaseResponse response = new BaseResponse();
//        System.out.println(request.toString());

        List<SysUser> list = sysUserService.findAllByUserName(request.getUserName());
        SysUser user = list.get(0);
        user.setUserName(request.getNewUserName());
        user.setREAL_NAME(request.getREAL_NAME());
        user.setSEX(request.getSEX());
        user.setEMAIL(request.getEMAIL());
        user.setPHONE(request.getPHONE());
        user.setMOBILE(request.getMOBILE());

        sysUserService.getMapper().save(user);

        response.setCode(1);
        response.setMsg(request.getNewUserName());

        return response;
    }

    @RequestMapping("/changePassword")
    public BaseResponse changePassword(@RequestBody ChangePassRequest request){
        BaseResponse response = new BaseResponse();
//        System.out.println(request.toString());
//        String MD5Password = Utils.getMD5(request.getPassword());
        String MD5Password2 = Utils.getMD5(request.getNewPassword());

        List<SysUser> list = sysUserService.findAllByUserName(request.getUserName());
        SysUser user = list.get(0);
//        System.out.println(user.getUserName()+user.getPassword()+"---------"+MD5Password);
//        if(MD5Password.equals(user.getPassword()))
//        {
            user.setPassword(MD5Password2);
            sysUserService.getMapper().save(user);
            response.setCode(1);
            response.setMsg(request.getUserName());
//        }
//        else{
//            response.setCode(0);
//            response.setMsg("密码验证失败");
//        }

        return response;
    }
}
