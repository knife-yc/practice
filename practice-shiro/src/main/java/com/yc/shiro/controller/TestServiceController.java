package com.yc.shiro.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestServiceController {

    @RequestMapping("/test")
    public void test(){
        System.out.println("----------------test--------------");
    }

    @RequestMapping("/testPerm")
    public void testPerm(){
        System.out.println("----------------testPerm--------------");
    }
}
