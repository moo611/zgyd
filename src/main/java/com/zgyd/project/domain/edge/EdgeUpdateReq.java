package com.zgyd.project.domain.edge;

import lombok.Data;

import java.io.Serializable;
@Data
public class EdgeUpdateReq implements Serializable {

    String id;
    /**
     * 边名称
     */
    String name;

    /**
     * 距离
     */
    String w;
    /**
     * 衰耗系数
     */
    String k;
    /**
     * 级别
     */
    String level;

}
