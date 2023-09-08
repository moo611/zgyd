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
     * 终点
     */
    @Column
    String tid;

    /**
     * 权重
     */
    @Column
    Float w;
}
