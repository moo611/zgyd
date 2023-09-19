package com.zgyd.project.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgyd.project.common.Response;
import com.zgyd.project.domain.node.NodeAddReq;
import com.zgyd.project.domain.node.NodeDao;
import com.zgyd.project.domain.node.NodePageReq;
import com.zgyd.project.domain.node.NodeUpdateReq;
import com.zgyd.project.mapper.NodeMapper;
import com.zgyd.project.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NodeService {
    @Autowired
    NodeMapper nodeMapper;

    static final int NODE_EXIST = 30001;
    static final int NODE_ADD_FAILED = 30002;
    static final int NODE_UPDATE_FAILED = 30003;
    static final int NODE_DELETE_FAILED = 30004;

    public Response save(NodeAddReq param) {

        NodeDao oldNode = nodeMapper.getNodeByName(param.getName());
        if (oldNode != null) {
            return Response.error(NODE_EXIST, "节点已存在");
        }

        NodeDao nodeDao = new NodeDao();
        BeanUtils.copyProperties(param, nodeDao);
        nodeDao.setCreateTime(DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", new Date()));
        int row = nodeMapper.insert(nodeDao);

        if (row == 0) {
            return Response.error(NODE_ADD_FAILED, "添加节点失败");
        }

        return Response.builder().success(true).msg("添加节点成功")
                .build();
    }


    /**
     * 分页获取节点
     *
     * @return
     */
    public Response<PageInfo<NodeDao>> getNodesPage(NodePageReq param) {

        PageHelper.startPage(param.getPage(), param.getCount());

        List<NodeDao> list = nodeMapper.getNodesPage(param.getQuery());
        PageInfo<NodeDao>pageInfo = new PageInfo<>(list);
        return new Response<>(true, pageInfo, 200);

    }

    public Response update(NodeUpdateReq param) {

        NodeDao nodeDao = new NodeDao();
        BeanUtils.copyProperties(param, nodeDao);

        int rows = nodeMapper.updateByPrimaryKeySelective(nodeDao);

        if (rows == 0) {
            return Response.error(NODE_UPDATE_FAILED, "更新节点失败");
        }

        return Response.builder().success(true).msg("更新节点成功")
                .build();

    }

    public Response delete(String id) {

        NodeDao nodeDao = new NodeDao();
        nodeDao.setId(id);
        nodeDao.setDelFlag("1");

        int rows = nodeMapper.updateByPrimaryKeySelective(nodeDao);
        if (rows == 0) {
            return Response.error(NODE_DELETE_FAILED, "删除节点失败");
        }

        return Response.builder().success(true).msg("删除节点成功")
                .build();
    }

}
