package com.zgyd.project.utils.tk;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Iterator;
import java.util.Set;

public class SpecialBatchProvider extends MapperTemplate {
    public SpecialBatchProvider(Class mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String batchInsertList(MappedStatement ms) {
        Class entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.insertIntoTable(entityClass, this.tableName(entityClass)));
        sql.append(SqlHelper.insertColumns(entityClass, false, false, false));
        sql.append(" VALUES ");
        sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" >");
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        Iterator var5 = columnList.iterator();

        while(var5.hasNext()) {
            EntityColumn column = (EntityColumn)var5.next();
            if (column.isInsertable()) {
                if (column.getJavaType() == Double.class) {
                    String record = column.getColumnHolder("record");
                    record = record.substring(0, record.length() - 1) + ",jdbcType=DECIMAL}";
                    sql.append(record + ",");
                } else {
                    sql.append(column.getColumnHolder("record") + ",");
                }
            }
        }

        sql.append("</trim>");
        sql.append("</foreach>");
        return sql.toString();
    }
}
