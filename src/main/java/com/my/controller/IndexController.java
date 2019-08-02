package com.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author QinHe at 2019-07-29
 */
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index() {
        System.out.println("index");
        System.out.println("web.root:" + System.getProperty("ssm-demo"));
        return "redirect:index.jsp";
    }
}
