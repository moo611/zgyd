package com.zgyd.project.mapper;

import com.zgyd.project.domain.node.NodeDao;
import com.zgyd.project.utils.tk.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NodeMapper extends MyMapper<NodeDao> {

    List<NodeDao>getAllNodes();

    NodeDao getNodeByName(@Param("name")String name);
}
