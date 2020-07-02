package com.example.demo.db.service;

import com.example.demo.db.mapper.OldpersonInfoMapper;
import com.example.demo.db.model.OldpersonInfo;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author CcQun、zsm
 * @Date 2020/6/29 21:08
 */
@Service
public class OldpersonInfoService extends BaseService<OldpersonInfo,Integer, OldpersonInfoMapper> {

    public OldpersonInfo findOldpersonByID(Integer id)
    {
        OldpersonInfo oldperson= OldpersonInfo.builder()
                .ID(id).build();

        Example<OldpersonInfo> example = Example.of(oldperson);
        List<OldpersonInfo> list = mapper.findAll(example);
        return list.get(0);
    }

    public OldpersonInfo findOldpersonById_card(String id_card)
    {
        OldpersonInfo oldperson= OldpersonInfo.builder()
                .id_card(id_card).build();

        Example<OldpersonInfo> example = Example.of(oldperson);
        List<OldpersonInfo> list = mapper.findAll(example);
        return list.get(list.size()-1);
    }
}
