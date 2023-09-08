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



}
