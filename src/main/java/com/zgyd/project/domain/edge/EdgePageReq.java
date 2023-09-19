package com.zgyd.project.domain.edge;

import lombok.Data;

import java.io.Serializable;
@Data
public class EdgePageReq implements Serializable {

    int page;
    int count;
    String query;
}
