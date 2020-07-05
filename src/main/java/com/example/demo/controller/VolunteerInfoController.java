package com.example.demo.controller;

import com.example.demo.core.Utils;
import com.example.demo.core.request.EmployeeRequest;
import com.example.demo.core.request.VolunteerRequest;
import com.example.demo.core.response.BaseResponse;
import com.example.demo.core.response.ListResponse;
import com.example.demo.db.model.EmployeeInfo;
import com.example.demo.db.model.VolunteerInfo;
import com.example.demo.core.crp.StatResponse;
import com.example.demo.db.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @Author fyl CcQun zsm
 * @Date 2020/6/30 17:07
 */
@RestController()
@RequestMapping("/volunteer")
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

    @Value("${web.upload-path}")
    private String imgPath;

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


    //录入义工信息/新增
    @RequestMapping("/addVolunteer")
    public BaseResponse addVolunteer(@RequestBody VolunteerRequest request) throws ParseException {

        Date day=new Date();
        System.out.println("加入义工记录一条");
        System.out.println("创建时间"+day.toString());
        BaseResponse response=new BaseResponse();
        VolunteerInfo temp=volunteerInfoService.findVolunteerById_card(request.getId_card());
        if(temp!=null){
            response.setCode(0);
            response.setMsg("this volunteer has already been in this system!!");
            return response;
        }
        VolunteerInfo volunteer= VolunteerInfo.builder()
                .id(getIDNumber()+1)
                .name(request.getName())
                .gender(request.getGender())
                .phone(request.getPhone())
                .id_card(request.getId_card())
                .birthday(Utils.strToDateLong(request.getBirthday()))
                .checkin_date(Utils.strToDateLong(request.getCheckin_date()))
                .DESCRIPTION(request.getDESCRIPTION())
                .ISACTIVE(request.getISACTIVE())
                .CREATED(day)
                .CREATEBY(request.getCREATEBY())
                .REMOVE("0")
                .build();

        volunteerInfoService.save(volunteer);
        System.out.println(request.getCREATEBY()+"加入成功");
        response.setCode(1);
        response.setMsg("add one oldperson successfully!!");
        return response;
    }

    //修改义工信息
    @RequestMapping("/editVolunteer")
    public BaseResponse editVolunteer(@RequestBody VolunteerRequest request) {

        Date day=new Date();
        // EmployeeInfo employee= EmployeeInfo.builder()
        VolunteerInfo volunteer= volunteerInfoService.findVolunteerByID(request.getId());
        System.out.println(volunteer.getName());

        volunteer.setGender(request.getGender());
        volunteer.setPhone(request.getPhone());
        volunteer.setId_card(request.getId_card());
        volunteer.setBirthday(Utils.strToDateLong(request.getBirthday()));
        volunteer.setCheckin_date(Utils.strToDateLong(request.getCheckin_date()));
        volunteer.setDESCRIPTION(request.getDESCRIPTION());
        volunteer.setISACTIVE(request.getISACTIVE());
        volunteer.setUPDATEBY(request.getUPDATEBY());
        volunteer.setUPDATED(day);

        volunteerInfoService.save(volunteer);
        BaseResponse reponse=new BaseResponse();

        reponse.setCode(1);
        reponse.setMsg("edit volunteer successfully!!");
        return reponse;
    }

    //查看义工信息
    @RequestMapping("/queryVolunteer")
    public ListResponse queryVolunteer() {
        System.out.println("查询开始");

        List<VolunteerInfo> volunteers = volunteerInfoService.findAll();
        List<VolunteerInfo> vols = new ArrayList<>();
        ListResponse response=new ListResponse();
        for(int i=0;i<volunteers.size();i++){
            VolunteerInfo volunteer=volunteers.get(i);
            if(volunteer.getREMOVE().equals("1")){
                continue;
            }
            vols.add(volunteer);
        }

        response.setCode(1);
        response.setMsg("query volunteer successfully!!");
        response.setData(vols);
        System.out.println("查询结束");
        return response;
    }

    //删除义工
    @RequestMapping("/removeVolunteer")
    public BaseResponse removeVolunteer(@RequestBody VolunteerRequest request) {
        Date day = new Date();
        BaseResponse response=new BaseResponse();
        VolunteerInfo volunteer=volunteerInfoService.findVolunteerByID(request.getId());
        System.out.println(volunteer.getName());
        volunteer.setREMOVE("1");
        volunteer.setCheckout_date(day);
        volunteerInfoService.save(volunteer);
        response.setCode(1);
        response.setMsg("delete volunteer successfully!!");
        return response;
    }

    //获得最大id
    public Integer getIDNumber() {
        List<VolunteerInfo> volunteers = volunteerInfoService.findAll();
        Integer idNumber = 3000;
        for (int i = 0; i < volunteers.size(); i++) {
            if (volunteers.get(i).getId() > idNumber) {
                idNumber = volunteers.get(i).getId();
            }
        }
        return idNumber;
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

    //运行python脚本
    @RequestMapping("/runPython")
    public BaseResponse runPython(@RequestBody VolunteerRequest request) {

        BaseResponse response=new BaseResponse();
        VolunteerInfo volunteer = volunteerInfoService.findVolunteerByID(request.getId());
        System.out.println(request.toString());

        String idNumber=volunteer.getId_card();
//        System.out.println("------------------"+idNumber);

        String result = "";
        try {
            //调用python，其中字符串数组对应的是python，python文件路径，向python传递的参数
            String[] strs=new String[] {"python","D:\\newdesktop\\test.py",idNumber};
            System.out.println(strs);
            //Runtime类封装了运行时的环境。每个 Java 应用程序都有一个 Runtime 类实例，使应用程序能够与其运行的环境相连接。
            //一般不能实例化一个Runtime对象，应用程序也不能创建自己的 Runtime 类实例，但可以通过 getRuntime 方法获取当前Runtime运行时对象的引用。
            // exec(String[] cmdarray) 在单独的进程中执行指定命令和变量。
            Process pr = Runtime.getRuntime().exec(strs);
            //使用缓冲流接受程序返回的结果
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream(),"GBK"));//注意格式
            //定义一个接受python程序处理的返回结果
            String line=" ";
            while((line=in.readLine())!=null) {
                //循环打印出运行的结果
                result+=line+" ";
            }
            //关闭in资源
            in.close();
            pr.waitFor();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("python传来的结果：");
        //打印返回结果
        System.out.println(result);
        response.setMsg(result);
        response.setCode(1);
        return response;
    }

    @RequestMapping("/addPhotoV")
    public BaseResponse addPhotoV(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "user") String id) throws IOException {
        BaseResponse response = new BaseResponse();
        VolunteerInfo volunteerInfo = volunteerInfoService.findVolunteerById_card(id);

        String base = imgPath+"images/volunteer/";
        String path=base+volunteerInfo.getId().toString()+"/";
        String fileName=volunteerInfo.getId().toString()+".jpg";
        System.out.println(path+fileName);

        volunteerInfo.setImgset_dir("images/volunteer/"+volunteerInfo.getId().toString()+"/"+volunteerInfo.getId().toString()+".jpg");
        if(file!=null&&id!=null){
            Utils.getImaFile(file,path,fileName);
        }else{
            response.setMsg("失败");
            response.setCode(0);
            return response;
        }

        volunteerInfoService.save(volunteerInfo);
        response.setCode(1);
        response.setMsg("义工头像设定成功");
        return response;
    }
}
