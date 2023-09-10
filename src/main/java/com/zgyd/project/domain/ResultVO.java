package com.zgyd.project.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class ResultVO implements Serializable {
    /**
     * 衰耗
     */
    float sum;
    /**
     * 路线
     */
    List<String>routeIds;
    /**
     * 路线
     */
    List<String>routeNames;

}
