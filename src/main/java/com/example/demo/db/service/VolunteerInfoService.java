package com.example.demo.db.service;

import com.example.demo.db.mapper.VolunteerInfoMapper;
import com.example.demo.db.model.EmployeeInfo;
import com.example.demo.db.model.VolunteerInfo;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author CcQun
 * @Date 2020/6/29 21:10
 */
@Service
public class VolunteerInfoService extends BaseService<VolunteerInfo,Integer, VolunteerInfoMapper>{

    public VolunteerInfo findVolunteerByID(Integer id)
    {
        System.out.println("id:"+id);
        VolunteerInfo volunteer= VolunteerInfo.builder()
                .id(id).build();
        Example<VolunteerInfo> example = Example.of(volunteer);
        List<VolunteerInfo> list = mapper.findAll(example);
        System.out.println(list.size());
        return list.get(0);
    }

    public VolunteerInfo findVolunteerById_card(String id_card)
    {
        VolunteerInfo volunteer= VolunteerInfo.builder()
                .id_card(id_card).build();

        Example<VolunteerInfo> example = Example.of(volunteer);
        List<VolunteerInfo> list = mapper.findAll(example);
        if(list.size()>0){
            return list.get(list.size()-1);
        }else{
            return null;
        }

    }
}
