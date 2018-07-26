package com.example.demo;

/**
 * Created by chenyl on 2018/7/25.
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.Date;

/**
 * 测试控制器
 * 该注解是 @Controller 和 @ResponseBody 注解的合体版
 * @author: @我没有三颗心脏
 * @create: 2018-05-08-下午 16:46
 */
//@RestController
    @Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello(Model m) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
        m.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));
        return "hello";
    }
}