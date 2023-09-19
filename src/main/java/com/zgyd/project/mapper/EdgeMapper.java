package com.zgyd.project.mapper;

import com.zgyd.project.domain.edge.EdgeDao;
import com.zgyd.project.domain.edge.EdgeVO;
import com.zgyd.project.utils.tk.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EdgeMapper extends MyMapper<EdgeDao> {


    EdgeDao getEdgeById(@Param("sid") String sid, @Param("tid") String tid);

    List<EdgeDao>getEdgeBySid(@Param("sid") String sid);

    List<EdgeVO>getEdgePage(@Param("query")String query);


}
