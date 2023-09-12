package com.zgyd.project.controller;

import com.github.pagehelper.PageInfo;
import com.zgyd.project.common.Response;
import com.zgyd.project.domain.node.NodeAddReq;
import com.zgyd.project.domain.node.NodeDao;
import com.zgyd.project.domain.node.NodePageReq;
import com.zgyd.project.domain.node.NodeUpdateReq;
import com.zgyd.project.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Response<PageInfo<NodeDao>>getNodes(@RequestBody NodePageReq param){

        return nodeService.getNodesPage(param);

    }

    @PutMapping("/update")
    public Response update(@RequestBody NodeUpdateReq param){

        return nodeService.update(param);
    }

    @DeleteMapping("/del")
    public Response delete(@RequestParam("id") String id){

        return nodeService.delete(id);
    }

}
