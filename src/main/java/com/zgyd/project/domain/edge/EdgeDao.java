package com.zgyd.project.domain.edge;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 记录边
 */
@Entity
@Table(name = "tb_edge")
@Data
public class EdgeDao {

    @Id
    String id;
    /**
     * 边名
     */
    @Column
    String name;

    /**
     * 起始点
     */
    @Column
    String sid;

    /**
     * 终点
     */
    @Column
    String tid;

    /**
     * 距离
     */
    @Column
    Float w;

    /**
     * 衰耗系数
     */
    @Column
    Float k;

    /**
     * 级别
     */
    @Column
    String level;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    String createTime;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @Column(name = "del_flag")
    String delFlag = "0";
}
