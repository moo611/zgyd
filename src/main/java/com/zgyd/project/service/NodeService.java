package com.zgyd.project.service;

import com.zgyd.project.common.Response;
import com.zgyd.project.domain.node.NodeAddReq;
import com.zgyd.project.domain.node.NodeDao;
import com.zgyd.project.mapper.NodeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NodeService {
    @Autowired
    NodeMapper nodeMapper;

    public Response save(NodeAddReq param) {

        NodeDao oldNode = nodeMapper.getNodeByName(param.getName());
        if (oldNode != null){
            return Response.error(30001,"节点已存在");
        }

        NodeDao nodeDao = new NodeDao();
        BeanUtils.copyProperties(param, nodeDao);

        int row = nodeMapper.insert(nodeDao);

        if(row == 0){
            return Response.error(30002,"添加节点失败");
        }

        return Response.builder().success(true).msg("添加节点成功")
                .build();
    }

}
