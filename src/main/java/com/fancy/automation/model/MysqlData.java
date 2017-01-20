package com.fancy.automation.model;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by puhui on 2017/1/12.
 */
public class MysqlData {
    /**数据库*/
    private String db ;
    /**表名*/
    private String table;
    /**where条件*/
    private JSONObject where = new JSONObject();
    /**update*/
    private JSONObject update = new JSONObject();
    /**insert*/
    private JSONObject insert = new JSONObject();

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
    private String jsonToCondition(JSONObject json){
        StringBuffer sql = new StringBuffer();
        json.forEach((key,value) -> sql.append("'").append(key).append("' = '").append(value).append("' and "));
        return sql.delete(sql.length()-5,sql.length()).toString();
    }

    /**将对象转为insert类型的可执行的sql语句*/
    public String toInsertSql(){
        try{
            if(insert.isEmpty()){
                throw new RuntimeException("insert is cannot be null, please check your data");
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }

        StringBuffer sql = new StringBuffer("INSERT INTO ");
        StringBuffer keys = new StringBuffer("(");
        StringBuffer values = new StringBuffer("(");
        insert.forEach((key,value) -> {
                    keys.append("'").append(key).append("',");
                    values.append("'").append(value).append("',");
                });
        keys.deleteCharAt(keys.length()-1).append(")");
        values.deleteCharAt(values.length()-1).append(")");
        sql.append(table).append(" ").append(keys).append(" VALUES ").append(values);
        return sql.toString();
    }

    /**将对象转为update类型的可执行的sql语句*/
    public String toUpdateSql(){
        StringBuffer sql = new StringBuffer("UPDATE ");
        sql.append(table).append(" SET ");
        try {
            //拼接set语句
            if(!update.isEmpty()){
                sql.append(jsonToCondition(update));
            }else {
                throw new RuntimeException("update语句中set不能为空");
            }

            //拼接where语句
            if(!where.isEmpty()){
                sql.append(" WHERE ").append(jsonToCondition(where));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sql.toString();
    }

    /**将对象转为select类型的可执行的sql语句*/
    public String toSelectSql(){
        StringBuffer sql = new StringBuffer("SELECT * FROM ");
        sql.append(table).append(" ");
        try {
            //拼接where语句
            if(!where.isEmpty()){
                sql.append(" where ").append(jsonToCondition(where));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sql.toString();
    }

    /**将对象转为delete类型的可执行的sql语句*/
    public String toDeleteSql(){
        StringBuffer sqlBuffer = new StringBuffer("Delete FROM ");
        sqlBuffer.append(table);
        try {
            //拼接where语句
            if(!where.isEmpty()){
                sqlBuffer.append(" where ").append(jsonToCondition(where));
            }else {
                throw new Exception("没有给删除的条件，你一下把数据库都删除了，我咋办呀");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sqlBuffer.toString();
    }
}
