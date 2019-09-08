package com.lxf.eye.show.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxf.eye.show.service.IndexService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class IndexController {
    @Resource
    private IndexService indexService;
    @RequestMapping("/queryData")
    @ResponseBody
    public String queryData() {
        JSONObject jsonObject = indexService.queryShowData();
        return JSON.toJSONString(jsonObject);
    }

    @RequestMapping("/")
    public ModelAndView index(Map map) {
        System.out.println("index");
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", "test");
        mv.setViewName("/index.html");
        return mv;
    }
}
