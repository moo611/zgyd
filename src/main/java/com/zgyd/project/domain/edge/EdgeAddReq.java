package com.zgyd.project.domain.edge;

import lombok.Data;

import java.io.Serializable;
@Data
public class EdgeAddReq implements Serializable {
    /**
     * 源地区
     */
    String sid;
    /**
     * 目标地区
     */
    String tid;
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
