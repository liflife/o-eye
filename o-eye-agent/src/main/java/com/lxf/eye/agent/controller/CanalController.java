package com.lxf.eye.agent.controller;

import com.lxf.eye.agent.service.CanalService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class CanalController {
    @Resource
    private CanalService orderService;
    @RequestMapping("/")
    @ResponseBody
    public String sayHello() {
        return "hello world:Canal";
    }

}
