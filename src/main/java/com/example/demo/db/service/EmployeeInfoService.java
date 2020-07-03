package com.example.demo.db.service;

import com.example.demo.db.mapper.EmployeeInfoMapper;
import com.example.demo.db.model.EmployeeInfo;
import com.example.demo.db.model.OldpersonInfo;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author CcQun zsm
 * @Date begin:2020/07/01  end:
 */
@Service
public class EmployeeInfoService extends BaseService<EmployeeInfo,Integer, EmployeeInfoMapper>{

    public EmployeeInfo findEmployeeByID(Integer id)
    {
        System.out.println("id:"+id);
        EmployeeInfo employee= EmployeeInfo.builder()
                .id(id).build();
        Example<EmployeeInfo> example = Example.of(employee);
        List<EmployeeInfo> list = mapper.findAll(example);
        System.out.println(list.size());
        return list.get(0);
    }

    public EmployeeInfo findEmployeeById_card(String id_card)
    {
        EmployeeInfo employee= EmployeeInfo.builder()
                .id_card(id_card).REMOVE("0").build();

        Example<EmployeeInfo> example = Example.of(employee);
        List<EmployeeInfo> list = mapper.findAll(example);
        if(list.size()>0){
            return list.get(0);
        }else{
            return null;
        }

    }
}
