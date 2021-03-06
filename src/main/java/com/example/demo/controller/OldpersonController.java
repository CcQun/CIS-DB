package com.example.demo.controller;

import com.example.demo.core.Utils;
import com.example.demo.core.request.OldpersonRequest;
import com.example.demo.core.response.BaseResponse;
import com.example.demo.core.response.ListResponse;
import com.example.demo.core.crp.OldStatResponse;
import com.example.demo.db.model.OldpersonInfo;
import com.example.demo.db.model.SysUser;
import com.example.demo.db.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author CcQun zsm fyl
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

    @Value("${web.upload-path}")
    private String imgPath;

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
        BaseResponse response = new BaseResponse();
        OldpersonInfo temp = oldpersonInfoService.findOldpersonById_card(request.getId_card());
        if (temp != null) {
            response.setCode(0);
            response.setMsg("this oldpersonn has already in this system!!");
            return response;
        }
        Date day = new Date();
        System.out.println("加入老人");
        System.out.println("创建时间" + day.toString());

        OldpersonInfo oldperson = OldpersonInfo.builder()
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
                .REMOVE("0")
                .build();
        System.out.println("开始插表");

        oldperson.setID(getIDNumber() + 1);
        oldperson.setCheckin_date(Utils.strToDateLong(request.getCheckin_date()));
        oldperson.setCREATED(day);
//        System.out.println(request.getCREATEBY());
//        System.out.println(request.getDESCRIPTION());
//        System.out.println(request.getISACTIVE());
        oldperson.setCREATEBY(request.getCREATEBY());
        oldpersonInfoService.save(oldperson);
        System.out.println(request.getCREATEBY() + "加入成功");
        response.setCode(1);
        response.setMsg("add one oldperson successfully!!");
        return response;
    }

    //修改老人信息
    @RequestMapping("/editOldPerson")
    public BaseResponse editOldPerson(@RequestBody OldpersonRequest request) {

        Date day = new Date();
        OldpersonInfo oldperson = oldpersonInfoService.findOldpersonByID(request.getID());
        System.out.println(oldperson.getUsername());
        oldperson.setGender(request.getGender());
        oldperson.setPhone(request.getPhone());
        oldperson.setId_card(request.getId_card());
        oldperson.setBirthday(Utils.strToDateLong(request.getBirthday()));
        oldperson.setCheckin_date(Utils.strToDateLong(request.getCheckin_date()));
        oldperson.setRoom_number(request.getRoom_number());
        oldperson.setFirstguardian_name(request.getFirstguardian_name());
        oldperson.setFirstguardian_phone(request.getFirstguardian_phone());
        oldperson.setFirstguardian_relationship(request.getFirstguardian_relationship());
        oldperson.setFirstguardian_wechat(request.getFirstguardian_wechat());
        oldperson.setSecondguardian_name(request.getSecondguardian_name());
        oldperson.setSecondguardian_phone(request.getSecondguardian_relationship());
        oldperson.setSecondguardian_phone(request.getSecondguardian_phone());
        oldperson.setFirstguardian_wechat(request.getSecondguardian_wechat());
        oldperson.setHealth_state(request.getHealth_state());
        oldperson.setDESCRIPTION(request.getDESCRIPTION());
        oldperson.setISACTIVE(request.getISACTIVE());
        oldperson.setUPDATEBY(request.getUPDATEBY());
        oldperson.setUPDATED(day);

        oldpersonInfoService.save(oldperson);
        BaseResponse reponse = new BaseResponse();

        reponse.setCode(1);
        reponse.setMsg("edit oldperson successfully!!");
        return reponse;
    }

    //查看老人信息
    @RequestMapping("/queryOldPerson")
    public ListResponse queryOldPerson() {
        System.out.println("查询开始");

        List<OldpersonInfo> oldpersons = oldpersonInfoService.findAll();
        List<OldpersonInfo> olds = new ArrayList<>();
        ListResponse response = new ListResponse();
        for (int i = 0; i < oldpersons.size(); i++) {
            OldpersonInfo oldperson = oldpersons.get(i);
            if (oldperson.getREMOVE().equals("1")) {
                continue;
            }
            olds.add(oldperson);
        }

        response.setCode(1);
        response.setMsg("query oldperson successfully!!");
        response.setData(olds);
        System.out.println("查询结束");
        return response;
    }

    //删除老人信息
    @RequestMapping("/removeOldPerson")
    public BaseResponse remove(@RequestBody OldpersonRequest request) {

        Date day = new Date();
        BaseResponse response = new BaseResponse();

        OldpersonInfo oldperson = oldpersonInfoService.findOldpersonByID(request.getID());
        System.out.println(oldperson.getUsername());
        oldperson.setREMOVE("1");
        oldperson.setCheckout_date(day);
        oldpersonInfoService.save(oldperson);
        response.setCode(1);
        response.setMsg("delete oldperson successfully!!");
        return response;
    }

    //获得最大id
    public Integer getIDNumber() {
        List<OldpersonInfo> oldpersons = oldpersonInfoService.findAll();
        Integer idNumber = 1000;
        for (int i = 0; i < oldpersons.size(); i++) {
            if (oldpersons.get(i).getID() > idNumber) {
                idNumber = oldpersons.get(i).getID();
            }
        }
        return idNumber;
    }

    @RequestMapping("/statOldPerson")//统计老人年龄分布，性别比例
    public OldStatResponse statOlePerson() throws Exception {
        OldStatResponse response = new OldStatResponse();
        List<OldpersonInfo> list = oldpersonInfoService.findAll();
        int female = 0;
        int male = 0;
        int level1 = 0;
        int level2 = 0;
        int level3 = 0;
        int[] a = new int[4];
        int total = list.size();
        if (total == 0) {
            response.setMsg("数据库中无老人信息");
            response.setCode(0);
            return response;
        } else {
            for (int i = 0; i < total; i++) {
                OldpersonInfo oldpersonInfo = list.get(i);
                int age = Utils.getAge(oldpersonInfo.getBirthday());

                if (oldpersonInfo.getREMOVE().equals("1")) {
                    continue;
                }
                //统计健康状况
                String[] s = oldpersonInfo.getHealth_state().split("_");
                for (int j = 0; j < 4; j++) {
                    if (s[j].equals("1")) {
                        a[j]++;
                    }
                }
                //统计年龄分布
                if (age < 60) {
                    level1++;
                } else if (age >= 60 && age < 70) {
                    level2++;
                } else {
                    level3++;
                }
                //统计性别
                if (oldpersonInfo.getGender().equals("男")) {
                    male++;
                } else {
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
        response.setNumberOfA(a[0]);
        response.setNumberOfB(a[1]);
        response.setNumberOfC(a[2]);
        response.setNumberOfD(a[3]);
        response.setCode(1);
        response.setMsg("统计信息返回");
        return response;
    }

    //老人头像设定
    @RequestMapping(value = "/addPhotoOP")
    public BaseResponse addPhotoOP(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "user") String id) throws IOException {
        BaseResponse response = new BaseResponse();
        System.out.println(id);
        OldpersonInfo oldpersonInfo = oldpersonInfoService.findOldpersonById_card(id);

        String base = imgPath + "images/oldPerson/";
        String path = base + oldpersonInfo.getID().toString() + "/";
        String fileName = oldpersonInfo.getID().toString() + ".jpg";
        System.out.println(path + fileName);
        //设置头像路径
        oldpersonInfo.setImgset_dir("images/oldPerson/" + oldpersonInfo.getID().toString() + "/" + oldpersonInfo.getID().toString() + ".jpg");

        if (file != null && id != null) {
            Utils.getImaFile(file, path, fileName);
        } else {
            response.setMsg("失败");
            response.setCode(0);
            return response;
        }

        oldpersonInfoService.save(oldpersonInfo);
        response.setCode(1);
        response.setMsg("老人头像设定成功");
        return response;
    }
}
