package com.zgyd.project.service;

import com.zgyd.project.common.Response;
import com.zgyd.project.domain.edge.EdgeDao;
import com.zgyd.project.domain.node.NodeDao;
import com.zgyd.project.mapper.NodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 项目核心代码，估值1个亿(日元)
 */
@Service
public class GraphService {

    @Autowired
    NodeService nodeService;

    @Autowired
    EdgeService edgeService;

    @Autowired
    NodeMapper nodeMapper;

    public Response<List<List<String>>> getRoutes(String sid, String tid) {

        List<List<String>> result = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        Map<String, List<EdgeDao>> map = loadNodesWithEdges();
        Map<String, Boolean> visited = new HashMap<>();
        cur.add(sid);
        visited.put(sid,true);
        def(result, cur, sid, tid, map, visited);
        return new Response<>(true, result, 200);

    }

    /**
     * @param res
     * @param cur
     * @param cid     当前节点id
     * @param tid     目标节点id
     * @param map
     * @param visited
     */
    private void def(List<List<String>> res, List<String> cur, String cid, String tid,
                     Map<String, List<EdgeDao>> map, Map<String, Boolean> visited) {
        if (cid.equals(tid)) {
            res.add(new ArrayList<>(cur));
            return;
        }
        //当前节点所有边
        List<EdgeDao> edgeDaos = map.get(cid);
        for (EdgeDao edgeDao:edgeDaos){

            String nextId = edgeDao.getTid();

            if (visited.get(nextId) == null || !visited.get(nextId)){
                cur.add(nextId);
                visited.put(nextId,true);
                def(res, cur, nextId, tid, map, visited);
                cur.remove(cur.size()-1);
                visited.put(nextId,false);
            }
        }

    }


    /**
     * 先把所有的节点和边加载到内存里
     * key 节点id
     * value 边数组
     */
    private Map<String, List<EdgeDao>> loadNodesWithEdges() {

        Map<String, List<EdgeDao>> resultMap = new HashMap<>();

        List<NodeDao> nodes = nodeMapper.getAllNodes();

        for (NodeDao node : nodes) {

            List<EdgeDao> edgeDaos = edgeService.edgeMapper.getEdgeBySid(node.getId());

            resultMap.put(node.getId(), edgeDaos);
        }

        return resultMap;
    }

}
