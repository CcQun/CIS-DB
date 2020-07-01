package com.example.demo.db.service;

import com.example.demo.db.mapper.SysUserMapper;
import com.example.demo.db.model.SysUser;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author CcQun
 * @Date 2020/6/29 21:09
 */
@Service
public class SysUserService extends BaseService<SysUser,Integer, SysUserMapper>{
    //通过用户名查找
    public List<SysUser> findAllByUserName(String username){
        SysUser user = SysUser.builder().UserName(username).build();
        Example<SysUser> example = Example.of(user);
        List<SysUser> list = mapper.findAll(example);
        return list;
    }
}
