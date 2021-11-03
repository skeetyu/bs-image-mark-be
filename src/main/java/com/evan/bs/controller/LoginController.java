package com.evan.bs.controller;

import com.evan.bs.result.Result;
import com.evan.bs.login.User;
import com.evan.bs.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "/api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser){
        // 对 html 标签进行转义，防止XSS攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        User user = userService.getLogin(username, requestUser.getPassword());
        if(user == null){
            String message = "账号密码错误";
            System.out.println(message);
            return new Result(400);
        }else{
            return new Result(200);
        }
    }
}
