package com.fancy.automation.model;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by puhui on 2017/1/12.
 */
public class MysqlData {
    /**数据库*/
    private String db;
    /**表名*/
    private String table;
    /**where条件*/
    private JSONObject where;
    /**update*/
    private JSONObject update;
    /**insert*/
    private JSONObject insert;

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public JSONObject getWhere() {
        return where;
    }

    public void setWhere(JSONObject where) {
        this.where = where;
    }

    public JSONObject getUpdate() {
        return update;
    }

    public void setUpdate(JSONObject update) {
        this.update = update;
    }

    public JSONObject getInsert() {
        return insert;
    }

    public void setInsert(JSONObject insert) {
        this.insert = insert;
    }


    /**将对象拼接成where或者set后跟的条件*/
    private String jsonToCondition(){
        StringBuffer sqlBuffer = new StringBuffer();
        where.forEach((key,value) -> sqlBuffer.append(key).append(" = '").append(value).append("' and "));
        return sqlBuffer.delete(-5,-1).toString();
    }

    /**将对象转为insert类型的可执行的sql语句*/
    public String toInsertSql(){
        StringBuffer sql = new StringBuffer("INSERT INTO ");
        StringBuffer keys = new StringBuffer("(");
        StringBuffer values = new StringBuffer("(");
        insert.forEach((key,value) -> {
                    keys.append(key).append(",");
                    values.append(value).append(",");
                });
        keys.deleteCharAt(-1).append(")");
        values.deleteCharAt(-1).append(")");
        sql.append(table).append(" ").append(keys).append(keys).append(" VALUES ").append(values);
        return sql.toString();
    }

    /**将对象转为update类型的可执行的sql语句*/
    public String toUpdateSql(){
        StringBuffer sqlBuffer = new StringBuffer("UPDATE ");
        sqlBuffer.append(table).append(" ");
        try {
            //拼接set语句
            if(!update.isEmpty()){
                sqlBuffer.append(jsonToCondition());
            }else {
                throw new Exception("update语句中set不能为空");
            }

            //拼接where语句
            if(!where.isEmpty()){
                sqlBuffer.append(" where ").append(jsonToCondition());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sqlBuffer.toString();
    }

    /**将对象转为select类型的可执行的sql语句*/
    public String toSelectSql(){
        StringBuffer sqlBuffer = new StringBuffer("SELECT * FROM ");
        sqlBuffer.append(table).append(" ");
        try {
            //拼接where语句
            if(!where.isEmpty()){
                sqlBuffer.append(" where ").append(jsonToCondition());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sqlBuffer.toString();
    }

    /**将对象转为delete类型的可执行的sql语句*/
    public String toDeleteSql(){
        StringBuffer sqlBuffer = new StringBuffer("Delete FROM ");
        sqlBuffer.append(table).append(" ");
        try {
            //拼接where语句
            if(!where.isEmpty()){
                sqlBuffer.append(" where ").append(jsonToCondition());
            }else {
                throw new Exception("没有给删除的条件，你一下把数据库都删除了，我咋办呀");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sqlBuffer.toString();
    }
}
