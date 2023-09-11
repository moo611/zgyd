package com.zgyd.project.service;

import com.github.pagehelper.PageHelper;
import com.zgyd.project.common.Response;
import com.zgyd.project.domain.edge.EdgeAddReq;
import com.zgyd.project.domain.edge.EdgeDao;
import com.zgyd.project.domain.edge.EdgePageReq;
import com.zgyd.project.mapper.EdgeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdgeService {

    @Autowired
    EdgeMapper edgeMapper;
    @Autowired
    NodeService nodeService;

    public Response save(EdgeAddReq param) {

        EdgeDao oldEdge = edgeMapper.getEdgeById(param.getSid(), param.getTid());
        if (oldEdge != null) {
            return Response.error(40001, "边已存在");
        }

        EdgeDao edgeDao = new EdgeDao();
        BeanUtils.copyProperties(param, edgeDao);
        edgeDao.setW(Float.valueOf(param.getW()));
        String sName = nodeService.getNodeById(param.getSid()).getName();
        String tName = nodeService.getNodeById(param.getTid()).getName();
        edgeDao.setSName(sName);
        edgeDao.setTName(tName);
        int row = edgeMapper.insert(edgeDao);

        if (row == 0) {
            return Response.error(40002, "添加边失败");
        }

        return Response.builder().success(true)
                .build();
    }


    /**
     * 分页查询
     * @return
     */
    public Response<List<EdgeDao>> getEdgeList(EdgePageReq param){

        PageHelper.startPage(param.getPage(), param.getCount());

        List<EdgeDao> list = edgeMapper.getEdgePage(param.getQuery());

        return new Response<>(true, list, 200);
    }

}
