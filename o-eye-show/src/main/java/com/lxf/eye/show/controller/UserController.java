package com.lxf.eye.show.controller;

import com.alibaba.fastjson.JSON;
import com.lxf.eye.common.domain.User;
import com.lxf.eye.show.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/queryUser")
    @ResponseBody
    public String queryUser(Integer id) {

        User user = userService.queryUser(id);
        return JSON.toJSONString(user);
    }
    @RequestMapping("/queryUserAll")
    @ResponseBody
    public String queryUserAll() {
        List<User> users = userService.queryUserAll();
        return JSON.toJSONString(users);
    }
}
