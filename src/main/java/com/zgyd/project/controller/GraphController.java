package com.zgyd.project.controller;

import com.github.pagehelper.PageInfo;
import com.zgyd.project.common.Response;
import com.zgyd.project.domain.ResultVO;
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
    Response<PageInfo<ResultVO>> getRoutes(@RequestParam("sid")String sid,
                                           @RequestParam("tid")String tid,
                                           @RequestParam(value = "level",required = false)String level){

        return graphService.getRoutes(sid, tid,level);

    }


}
