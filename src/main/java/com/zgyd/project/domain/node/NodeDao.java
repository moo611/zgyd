package com.zgyd.project.domain.node;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_node")
@Data
public class NodeDao {

    @Id
    String id;

    /**
     * 节点名称
     */
    @Column
    String name;

    @Column(name = "create_time")
    String createTime;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @Column(name = "del_flag")
    String delFlag = "0";
}
