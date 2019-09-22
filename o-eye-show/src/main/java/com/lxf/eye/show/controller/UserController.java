package com.lxf.eye.show.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxf.eye.common.domain.OrderInfo;
import com.lxf.eye.common.domain.User;
import com.lxf.eye.common.domain.api.OrderServiceDubbo;
import com.lxf.eye.show.conf.EyeShowConf;
import com.lxf.eye.show.service.IndexService;
import com.lxf.eye.show.service.UserService;
import com.xxl.conf.core.annotation.XxlConf;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private EyeShowConf eyeShowConf;

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
    @RequestMapping("/queryVersion")
    @ResponseBody
    public String queryVersion() {
        System.out.printf("version:"+eyeShowConf.version);
        return JSON.toJSONString(eyeShowConf.version);
    }
}
