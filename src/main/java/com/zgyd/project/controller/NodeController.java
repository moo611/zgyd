package com.zgyd.project.controller;

import com.zgyd.project.common.Response;
import com.zgyd.project.domain.node.NodeAddReq;
import com.zgyd.project.domain.node.NodeDao;
import com.zgyd.project.domain.node.NodePageReq;
import com.zgyd.project.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/node")
public class NodeController {

    @Autowired
    NodeService nodeService;

    @PostMapping("/add")
    public Response addNode(@RequestBody NodeAddReq param){

        return nodeService.save(param);

    }

    @PostMapping("/list")
    public Response<List<NodeDao>>getNodes(@RequestBody NodePageReq param){

        return nodeService.getNodesPage(param);

    }

}
