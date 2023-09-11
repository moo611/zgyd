package com.zgyd.project.domain.node;

import lombok.Data;

import java.io.Serializable;

@Data
public class NodePageReq implements Serializable {


    int page;
    int count;
    String query;

}
