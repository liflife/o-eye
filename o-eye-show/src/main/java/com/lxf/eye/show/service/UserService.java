package com.lxf.eye.show.service;

import com.alibaba.fastjson.JSONObject;
import com.lxf.eye.common.domain.User;
import com.lxf.eye.show.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    public User queryUser(Integer id) {
       return userMapper.selectByPrimaryKey(id);
    }

    public List<User> queryUserAll() {
        return userMapper.selectAll();
    }
}
