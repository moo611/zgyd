package com.zgyd.project.utils.tk;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

@RegisterMapper
public interface SpecialBatchMapper<T> {
    @Options(
            useGeneratedKeys = true,
            keyProperty = "id"
    )
    @InsertProvider(
            type = SpecialBatchProvider.class,
            method = "batchInsertList"
    )
    int batchInsertList(List<T> var1);
}
