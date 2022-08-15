package com.brotao.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author luotao
 * @Date 15/8/2022
 * @Description
 */

@RestController
@ConditionalOnProperty(name = "hello.enabled", havingValue = "true")
public class HelloController {
    
    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
