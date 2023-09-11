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
    Long id;
    /**
     * 起始点
     */
    @Column
    String sid;

    /**
     * 起始点名称
     */
    @Column(name = "s_name")
    String sName;
    /**
     * 终点
     */
    @Column
    String tid;
    /**
     * 终点名称
     */
    @Column(name = "t_name")
    String tName;
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
}
