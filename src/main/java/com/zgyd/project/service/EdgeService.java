package com.zgyd.project.service;

import com.zgyd.project.common.Response;
import com.zgyd.project.domain.edge.EdgeAddReq;
import com.zgyd.project.domain.edge.EdgeDao;
import com.zgyd.project.mapper.EdgeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdgeService {

    @Autowired
    EdgeMapper edgeMapper;

    public Response save(EdgeAddReq param) {

        EdgeDao oldEdge = edgeMapper.getEdgeById(param.getSid(), param.getTid());
        if (oldEdge != null) {
            return Response.error(40001, "边已存在");
        }

        EdgeDao edgeDao = new EdgeDao();
        BeanUtils.copyProperties(param, edgeDao);
        edgeDao.setW(Float.valueOf(param.getW()));
        int row = edgeMapper.insert(edgeDao);

        if (row == 0) {
            return Response.error(40002, "添加边失败");
        }

        return Response.builder().success(true)
                .build();
    }


    public Response<List<EdgeDao>> getEdges(String sid) {
        List<EdgeDao> res = edgeMapper.getEdgeBySid(sid);
        return new Response<>(true, res, 200);

    }
}
