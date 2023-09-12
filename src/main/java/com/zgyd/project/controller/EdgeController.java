package com.zgyd.project.controller;

import com.zgyd.project.common.Response;
import com.zgyd.project.domain.edge.EdgeAddReq;
import com.zgyd.project.domain.edge.EdgePageReq;
import com.zgyd.project.domain.edge.EdgeUpdateReq;
import com.zgyd.project.domain.edge.EdgeVO;
import com.zgyd.project.service.EdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edge")
public class EdgeController {

    @Autowired
    EdgeService edgeService;

    @PostMapping("/add")
    public Response addEdge(@RequestBody EdgeAddReq param){

        return edgeService.save(param);

    }


    @PostMapping("/list")
    public Response<List<EdgeVO>>getNodes(@RequestBody EdgePageReq param){

        return edgeService.getEdgeList(param);

    }

    @PutMapping("/update")
    public Response update(@RequestBody EdgeUpdateReq param){

        return edgeService.update(param);
    }

    @DeleteMapping("/del")
    public Response delete(@RequestParam("id") String id){

        return edgeService.delete(id);
    }

}
