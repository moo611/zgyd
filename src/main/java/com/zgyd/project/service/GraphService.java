package com.zgyd.project.service;

import com.github.pagehelper.PageInfo;
import com.zgyd.project.common.Response;
import com.zgyd.project.domain.ResultVO;
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

    public static float tdxs = 1;

    @Autowired
    NodeService nodeService;

    @Autowired
    EdgeService edgeService;

    @Autowired
    NodeMapper nodeMapper;

    public Response<PageInfo<ResultVO>> getRoutes(String sid, String tid, String level) {

        List<ResultVO> result = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        Map<String, List<EdgeDao>> map = loadNodesWithEdges();
        Map<String, Boolean> visited = new HashMap<>();
        cur.add(sid);
        visited.put(sid, true);
        float sum = 0;
        def(result, cur, sid, tid, map, visited, sum, level);

        //地名
        for (ResultVO resultVO : result) {
            List<String> names = nodeMapper.getNameByIds(resultVO.getRouteIds());
            resultVO.setRouteNames(names);
        }
        PageInfo<ResultVO>pageInfo = new PageInfo<>(result);
        return new Response<>(true, pageInfo, 200);

    }

    /**
     * @param res
     * @param cur
     * @param cid     当前节点id
     * @param tid     目标节点id
     * @param map
     * @param visited
     */
    private void def(List<ResultVO> res,
                     List<String> cur,
                     String cid,
                     String tid,
                     Map<String, List<EdgeDao>> map,
                     Map<String, Boolean> visited,
                     float sum,
                     String level) {
        if (cid.equals(tid)) {

            ResultVO resultVO = new ResultVO();
            resultVO.setRouteIds(new ArrayList<>(cur));

            sum += (cur.size()-1) * tdxs;

            resultVO.setSum(sum);
            res.add(resultVO);
            return;
        }
        //当前节点所有边
        List<EdgeDao> edgeDaos = map.get(cid);

        if (edgeDaos==null){
            //这个点被删除了，导致查不出边
            return;
        }

        for (EdgeDao edgeDao : edgeDaos) {

            if (level != null && !edgeDao.getLevel().equals(level)) {
                continue;
            }

            String nextId = edgeDao.getTid();
            float consume = edgeDao.getW() * edgeDao.getK();
            if (visited.get(nextId) == null || !visited.get(nextId)) {
                cur.add(nextId);
                visited.put(nextId, true);
                sum += consume;
                def(res, cur, nextId, tid, map, visited, sum, level);
                sum -= consume;
                cur.remove(cur.size() - 1);
                visited.put(nextId, false);
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
        System.out.println("starttime:"+System.currentTimeMillis());
        for (NodeDao node : nodes) {

            List<EdgeDao> edgeDaos = edgeService.edgeMapper.getEdgeBySid(node.getId());

            resultMap.put(node.getId(), edgeDaos);
        }
        System.out.println("endtime:"+System.currentTimeMillis());
        return resultMap;
    }

}
