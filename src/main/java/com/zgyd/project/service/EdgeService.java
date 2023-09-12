package com.zgyd.project.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgyd.project.common.Response;
import com.zgyd.project.domain.edge.*;
import com.zgyd.project.mapper.EdgeMapper;
import com.zgyd.project.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EdgeService {

    @Autowired
    EdgeMapper edgeMapper;
    @Autowired
    NodeService nodeService;
    
    static final int EDGE_EXIST = 40001;
    static final int EDGE_ADD_FAILED = 40002;
    static final int EDGE_UPDATE_FAILED = 40003;
    static final int EDGE_DELETE_FAILED = 40004;
    static final int EDGE_INVALID = 40005;

    public Response save(EdgeAddReq param) {

        if (param.getSid().equals(param.getTid())){
            return Response.error(EDGE_INVALID, "不能添加两节点相同的边");
        }

        EdgeDao oldEdge = edgeMapper.getEdgeById(param.getSid(), param.getTid());
        if (oldEdge != null) {
            return Response.error(EDGE_EXIST, "边已存在");
        }


        EdgeDao edgeDao = new EdgeDao();
        BeanUtils.copyProperties(param, edgeDao);
        edgeDao.setW(Float.valueOf(param.getW()));
        edgeDao.setK(Float.valueOf(param.getK()));
        edgeDao.setCreateTime(DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", new Date()));
        int row = edgeMapper.insert(edgeDao);

        if (row == 0) {
            return Response.error(EDGE_ADD_FAILED, "添加边失败");
        }

        return Response.builder().success(true)
                .build();
    }


    /**
     * 分页查询
     * @return
     */
    public Response<PageInfo<EdgeVO>> getEdgeList(EdgePageReq param){

        PageHelper.startPage(param.getPage(), param.getCount());

        List<EdgeVO> list = edgeMapper.getEdgePage(param.getQuery());
        PageInfo<EdgeVO>pageInfo = new PageInfo<>(list);
        return new Response<>(true, pageInfo, 200);
    }

    public Response update(EdgeUpdateReq param) {

        EdgeDao edgeDao = new EdgeDao();
        BeanUtils.copyProperties(param,edgeDao);

        int rows = edgeMapper.updateByPrimaryKeySelective(edgeDao);

        if (rows == 0) {
            return Response.error(EDGE_UPDATE_FAILED, "更新边失败");
        }

        return Response.builder().success(true).msg("更新边成功")
                .build();

    }

    public Response delete(String id) {

        EdgeDao edgeDao = new EdgeDao();
        edgeDao.setId(id);
        edgeDao.setDelFlag("1");

        int rows = edgeMapper.updateByPrimaryKeySelective(edgeDao);
        if (rows == 0) {
            return Response.error(EDGE_DELETE_FAILED, "删除边失败");
        }

        return Response.builder().success(true).msg("删除边成功")
                .build();
    }

}
