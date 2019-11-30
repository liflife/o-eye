package com.lxf.eye.oauth.controller;


import com.lxf.eye.common.http.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OauthController {
    public static String accessUrl="https://github.com/login/oauth/access_token?";
    public static String clientID="5f370d133a5f1874d01c";
    public static String clientSecret="6805af9cf984387b3228acf7d63542efce35614f";
    public static String code="";
    @RequestMapping("/login")
    @ResponseBody
    public String login(String code) {
        return "index";
    }
    @RequestMapping("/oauth/redirect")
    public ModelAndView oauth(String code) {
        String url=accessUrl+"client_id="+clientID+"&client_secret="+clientSecret+"&code="+code;
        String token = HttpUtil.call(url);
        String[] split = token.split("&");
        String[] split1 = split[0].split("=");
        System.out.println(token);
        HttpUtil.setAccessToken(split1[1]);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.getModelMap().addAttribute("token",token);
        return modelAndView;
    }
    @RequestMapping("/followers")
    @ResponseBody
    public String followersList() {
        String url="https://api.github.com/user/repos?page=1&per_page=100";
        String accessToken = HttpUtil.getAccessToken();
        System.out.println("accessToken:"+accessToken);
        String result = HttpUtil.callJson(url,accessToken);
        System.out.println("result:"+result);
//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.setViewName("index");
//        modelAndView.getModelMap().addAttribute("result",result);
        return result;
    }
}
