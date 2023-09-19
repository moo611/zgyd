package com.zgyd.project.domain.edge;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class EdgeVO implements Serializable {


    Long id;

    /**
     * 边名
     */
    String name;
    /**
     * 起始点
     */
    String sid;

    /**
     * 起始点名称
     */

    String sName;
    /**
     * 终点
     */

    String tid;
    /**
     * 终点名称
     */

    String tName;
    /**
     * 距离
     */

    Float w;

    /**
     * 衰耗系数
     */

    Float k;

    /**
     * 级别
     */

    String level;

    /**
     * 创建时间
     */
    String createTime;
}
