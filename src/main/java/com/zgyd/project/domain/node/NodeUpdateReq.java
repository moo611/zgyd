package com.zgyd.project.domain.node;

import lombok.Data;

import java.io.Serializable;

@Data
public class NodeUpdateReq implements Serializable {

    String id;
    String name;
}
