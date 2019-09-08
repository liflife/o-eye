package com.lxf.eye.common.domain;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author: create by xingfeng.luo
 * @version: v1.0
 * @description: CanalMysqlEntry
 * @date:2019/9/5
 **/
public class CanalMysqlEntry {
    private String dataBaseName;
    private String tableName;
    private String type;
    private Map<String, Object> dataBefore;
    private Map<String, Object> dataAfter;

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getDataBefore() {
        return dataBefore;
    }

    public void setDataBefore(Map<String, Object> dataBefore) {
        this.dataBefore = dataBefore;
    }

    public Map<String, Object> getDataAfter() {
        return dataAfter;
    }

    public void setDataAfter(Map<String, Object> dataAfter) {
        this.dataAfter = dataAfter;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
