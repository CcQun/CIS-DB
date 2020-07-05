package com.example.demo.controller;

import com.example.demo.core.Utils;
import com.example.demo.core.request.EmployeeRequest;
import com.example.demo.core.response.BaseResponse;
import com.example.demo.core.response.ListResponse;
import com.example.demo.db.model.EmployeeInfo;
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
 * @Author zsm fyl CcQun
 * @Date 2020/6/30 17:04
 */

@RestController()
@RequestMapping("/employee")
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

    @Value("${web.upload-path}")
    private String imgPath;


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

    //录入工作人员信息/新增
    @RequestMapping("/addEmployee")
    public BaseResponse addEmployee(@RequestBody EmployeeRequest request) throws ParseException {

        Date day = new Date();
        System.out.println("加入工作人员");
        System.out.println("创建时间" + day.toString());
        BaseResponse response = new BaseResponse();

        EmployeeInfo temp = employeeInfoService.findEmployeeById_card(request.getId_card());
        //System.out.println("查找结束"+temp.toString());
        boolean isExist = false;
        if (temp != null) {

            isExist = true;

        }
        if (!isExist) {
            EmployeeInfo employee = EmployeeInfo.builder()
                    .id(getIDNumber() + 1)
                    .username(request.getUsername())
                    .gender(request.getGender())
                    .phone(request.getPhone())
                    .id_card(request.getId_card())
                    .birthday(Utils.strToDateLong(request.getBirthday()))
                    .hire_date(Utils.strToDateLong(request.getHire_date()))
                    .DESCRIPTION(request.getDESCRIPTION())
                    .ISACTIVE(request.getISACTIVE())
                    .CREATED(day)
                    .CREATEBY(request.getCREATEBY())
                    .REMOVE("0")
                    .build();

            employeeInfoService.save(employee);
            System.out.println(request.getCREATEBY() + "加入成功");
            response.setCode(1);
            response.setMsg("add one emloyee successfully!!");
        } else {
            response.setCode(0);
            response.setMsg("this empoyee has already been in system!!");
        }
        return response;
    }


    //修改工作人员信息
    @RequestMapping("/editEmployee")
    public BaseResponse editEmployee(@RequestBody EmployeeRequest request) {

        Date day = new Date();
        // EmployeeInfo employee= EmployeeInfo.builder()
        EmployeeInfo employee = employeeInfoService.findEmployeeByID(request.getId());
        System.out.println(employee.getUsername());

        employee.setGender(request.getGender());
        employee.setPhone(request.getPhone());
        employee.setId_card(request.getId_card());
        employee.setBirthday(Utils.strToDateLong(request.getBirthday()));
        employee.setHire_date(Utils.strToDateLong(request.getHire_date()));
        employee.setDESCRIPTION(request.getDESCRIPTION());
        employee.setISACTIVE(request.getISACTIVE());
        employee.setUPDATEBY(request.getUPDATEBY());
        employee.setUPDATED(day);

        employeeInfoService.save(employee);
        BaseResponse reponse = new BaseResponse();

        reponse.setCode(1);
        reponse.setMsg("edit employee successfully!!");
        return reponse;
    }


    //查看工作人员信息
    @RequestMapping("/queryEmployee")
    public ListResponse queryEmployee() {
        System.out.println("查询开始");

        List<EmployeeInfo> employees = employeeInfoService.findAll();
        List<EmployeeInfo> employ = new ArrayList<>();
        ListResponse response = new ListResponse();
        for (int i = 0; i < employees.size(); i++) {
            EmployeeInfo employee = employees.get(i);
            if (employee.getREMOVE().equals("1")) {
                continue;
            }
            employ.add(employee);
        }

        response.setCode(1);
        response.setMsg("query employee successfully!!");
        response.setData(employ);
        System.out.println("查询结束");
        return response;
    }

    //删除员工
    @RequestMapping("/removeEmployee")
    public BaseResponse removeEmployee(@RequestBody EmployeeRequest request) {

        // EmployeeInfo employee= EmployeeInfo.builder()
        Date day = new Date();
        BaseResponse response = new BaseResponse();
        EmployeeInfo employee = employeeInfoService.findEmployeeByID(request.getId());
        System.out.println(employee.getUsername());
        employee.setREMOVE("1");
        employee.setResign_date(day);
        employeeInfoService.save(employee);
        response.setCode(1);
        response.setMsg("delete employee successfully!!");
        return response;
    }

    //获得最大id
    public Integer getIDNumber() {
        List<EmployeeInfo> employees = employeeInfoService.findAll();
        Integer idNumber = 2000;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() > idNumber) {
                idNumber = employees.get(i).getId();
            }
        }
        return idNumber;
    }

    //员工信息统计
    @RequestMapping("/statEmployee")
    public StatResponse statEmployee() throws Exception {
        StatResponse response = new StatResponse();
        List<EmployeeInfo> list = employeeInfoService.findAll();
        int female = 0;
        int male = 0;
        int level1 = 0;
        int level2 = 0;
        int level3 = 0;
        int total = list.size();
        if (total <= 0) {
            response.setMsg("数据库中无工作人员信息");
            response.setCode(0);
            return response;
        } else {
            for (int i = 0; i < total; i++) {
                EmployeeInfo employeeInfo = list.get(i);
                int age = Utils.getAge(employeeInfo.getBirthday());
                System.out.println(employeeInfo.getUsername() + "_age:" + age);
                //统计年龄分布
                if (age < 30) {
                    level1++;
                } else if (age >= 30 && age < 40) {
                    level2++;
                } else {
                    level3++;
                }

                //统计性别
                if (employeeInfo.getGender().equals("男")) {
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
        response.setCode(1);
        response.setMsg("统计信息返回");
        return response;
    }

    //运行python脚本
    @RequestMapping("/runPython")
    public BaseResponse runPython(@RequestBody EmployeeRequest request) {

        BaseResponse response = new BaseResponse();
        EmployeeInfo employee = employeeInfoService.findEmployeeByID(request.getId());
        System.out.println(request.toString());

        String idNumber = employee.getId_card();
//        System.out.println("------------------"+idNumber);

        String result = "";
        try {
            //调用python，其中字符串数组对应的是python，python文件路径，向python传递的参数
            String[] strs = new String[]{"python", "D:\\newdesktop\\test.py", idNumber};
            System.out.println(strs);
            //Runtime类封装了运行时的环境。每个 Java 应用程序都有一个 Runtime 类实例，使应用程序能够与其运行的环境相连接。
            //一般不能实例化一个Runtime对象，应用程序也不能创建自己的 Runtime 类实例，但可以通过 getRuntime 方法获取当前Runtime运行时对象的引用。
            // exec(String[] cmdarray) 在单独的进程中执行指定命令和变量。
            Process pr = Runtime.getRuntime().exec(strs);
            //使用缓冲流接受程序返回的结果
            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream(), "GBK"));//注意格式
            //定义一个接受python程序处理的返回结果
            String line = " ";
            while ((line = in.readLine()) != null) {
                //循环打印出运行的结果
                result += line + " ";
            }
            //关闭in资源
            in.close();
            pr.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("python传来的结果：");
        //打印返回结果
        System.out.println(result);
        response.setMsg(result);
        response.setCode(1);
        return response;
    }

    @RequestMapping("/addPhotoE")
    public BaseResponse addPhotoE(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "user") String id) throws IOException {
        BaseResponse response = new BaseResponse();
        EmployeeInfo employeeInfo = employeeInfoService.findEmployeeById_card(id);

        String base = imgPath + "images/employee/";
        String path = base + employeeInfo.getId().toString() + "/";
        String fileName = employeeInfo.getId().toString() + ".jpg";
        System.out.println(path + fileName);

        employeeInfo.setImgset_dir("images/employee/" + employeeInfo.getId().toString() + "/" + employeeInfo.getId().toString() + ".jpg");
        if (file != null && id != null) {
            Utils.getImaFile(file, path, fileName);
        } else {
            response.setMsg("失败");
            response.setCode(0);
            return response;
        }

        employeeInfoService.save(employeeInfo);
        response.setCode(1);
        response.setMsg("员工头像设定成功");
        return response;
    }
}
