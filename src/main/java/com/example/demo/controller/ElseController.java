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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    //解释器路径
    @Value("${InterpreterPath}")
    private String interpreterPath;
    //脚本路径

    @Value("${pythonPath}")
    private String pyPath;

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


    //运行人脸采集脚本
    @RequestMapping("/runFaceCollectPython")
    public BaseResponse runFaceCollectPython(@RequestParam(value = "ID") String ID, @RequestParam(value = "userID") String userID, @RequestParam(value="type") String type) {
        BaseResponse response=new BaseResponse();
        System.out.println("管理员ID："+userID);
        System.out.println("id："+ID);
        System.out.println("采集类型："+type);
        String result = ID+" "+userID;
        try {
            //调用python，其中字符串数组对应的是python，python文件路径，向python传递的参数
            String[] strs=new String[] {interpreterPath,pyPath,ID,userID,type};
            //Runtime类封装了运行时的环境。每个 Java 应用程序都有一个 Runtime 类实例，使应用程序能够与其运行的环境相连接。
            //一般不能实例化一个Runtime对象，应用程序也不能创建自己的 Runtime 类实例，但可以通过 getRuntime 方法获取当前Runtime运行时对象的引用。
            // exec(String[] cmdarray) 在单独的进程中执行指定命令和变量。
            System.out.println(strs[1]+"_"+pyPath);
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

    @RequestMapping("/cffeedback")
    public BaseResponse cffeedback(@RequestBody MessageRequest request) throws IOException {
        System.out.println(request.toString());
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

    @RequestMapping("/trainfrfeedback")
    public BaseResponse trainfrfeedback(@RequestBody MessageRequest request) throws IOException {
        System.out.println(request.toString());
        WebSocketServer.sendInfo(request.getMessage(),request.getUserId());
        BaseResponse response=new BaseResponse();
        response.setCode(1);
        response.setMsg("已传送");
        return response;
    }
}
