package com.zgyd.project.controller;

import com.zgyd.project.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {

    @Autowired
    TestService testService;
    @GetMapping("/test")
    public String test(){

        return testService.test();
    }


}
