package com.my.controller;

import com.my.entity.User;
import com.my.model.CommonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author QinHe at 2019-07-31
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login.htm")
    @ResponseBody
    public CommonResult<Object> login(String username, String password) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(usernamePasswordToken);
        return CommonResult.success();
    }

    @GetMapping("/showName.htm")
    @ResponseBody
    public CommonResult<String> showName() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return CommonResult.success(user.getUsername());
    }

    @PostMapping("/logout.htm")
    @ResponseBody
    public CommonResult<String> logout() {
        SecurityUtils.getSubject().logout();
        return CommonResult.success();
    }

}
