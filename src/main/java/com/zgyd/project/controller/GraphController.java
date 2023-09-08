package com.zgyd.project.controller;

import com.zgyd.project.common.Response;
import com.zgyd.project.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/graph")
public class GraphController {

    @Autowired
    GraphService graphService;
    @GetMapping("/list")
    Response<List<List<String>>> getRoutes(@RequestParam("sid")String sid,@RequestParam("tid")String tid){

        return graphService.getRoutes(sid, tid);

    }


}
