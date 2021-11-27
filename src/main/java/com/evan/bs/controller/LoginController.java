package com.evan.bs.controller;

import com.evan.bs.entity.Password;
import com.evan.bs.entity.User;
import com.evan.bs.result.Result;
import com.evan.bs.service.UserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
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

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, requestUser.getPassword());
        
        try{
            subject.login(usernamePasswordToken);
            return new Result(200);
        }catch(AuthenticationException e){
            return new Result(400);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/api/register")
    @ResponseBody
    public Result register(@RequestBody User requestUser){
        System.out.println("Registering");
        // 对 html 标签进行转义，防止XSS攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        if(userService.existByName(username)) return new Result(401);   // 用户名已注册

        String email = requestUser.getEmail();
        email = HtmlUtils.htmlEscape(email);
        if(userService.existByEmail(email)) return new Result(402);     // 该邮箱已注册

        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        String password = requestUser.getPassword();
        String encodedPassword = new SimpleHash("md5", password, salt, 1).toString();
        User user = new User(username, encodedPassword, email);
        user.setSalt(salt);
        userService.add(user);
        return new Result(200); // 成功注册
    }

    @CrossOrigin
    @PostMapping(value = "/api/logout")
    @ResponseBody
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result(200); // 成功登出
    }

    @CrossOrigin
    @PostMapping(value = "/api/unsubscribe")
    @ResponseBody
    public Result unsubscribe(){
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        userService.deleteByName(username);
        return new Result(200); // 成功注销
    }

    @CrossOrigin
    @PostMapping(value = "/api/editpassword")
    @ResponseBody
    public Result editpassword(@RequestBody Password password){
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getByName(username);
        String salt = user.getSalt();
        String passwordInDB = new SimpleHash("md5", password.getOldpassword(), salt, 1).toString();
        System.out.println(user.getPassword());
        System.out.println(passwordInDB);
        if(!user.getPassword().equals(passwordInDB)) return new Result(400); // 旧密码错误
 
        userService.deleteByName(username);
        String newsalt = new SecureRandomNumberGenerator().nextBytes().toString();
        String encodedPassword = new SimpleHash("md5", password.getNewpassword(), newsalt, 1).toString();
        user.setSalt(newsalt);
        user.setPassword(encodedPassword);
        userService.add(user);
        return new Result(200); // 成功注销
    }


    @CrossOrigin
    @GetMapping(value = "/api/authentication")
    @ResponseBody
    public Result authentication(){
        System.out.println("authentication");
        return new Result(1000);
    }

    @CrossOrigin
    @PostMapping(value = "/api/getuser")
    @ResponseBody
    public User getuser(){
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getByName(username);
        return user;
    }
}
