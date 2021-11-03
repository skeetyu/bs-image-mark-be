package com.evan.bs.controller;

import com.evan.bs.result.Result;
import com.evan.bs.login.User;
import com.evan.bs.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@Controller
public class RegisterController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "/api/register")
    @ResponseBody
    public Result Register(@RequestBody User requestUser){
        // 对 html 标签进行转义，防止XSS攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        if(userService.existByName(username)) return new Result(401);   // 用户名已注册

        String email = requestUser.getEmail();
        email = HtmlUtils.htmlEscape(email);
        if(userService.existByEmail(email)) return new Result(402);     // 该邮箱已注册

        User user = new User(username, requestUser.getPassword(), email);
        userService.add(user);
        return new Result(200); // 成功注册
    }
}
