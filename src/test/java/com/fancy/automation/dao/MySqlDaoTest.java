package com.fancy.automation.dao;

import com.alibaba.fastjson.JSONObject;
import com.fancy.automation.model.MysqlData;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by puhui on 2017/1/20.
 */

public class MySqlDaoTest {

    private MysqlData mysqlData = new MysqlData();

    @Before
    public void init(){
        JSONObject insert = new JSONObject();
        insert.put("create_time","2050-11-12 13:00:00");
        insert.put("name","nrf");
        JSONObject update = new JSONObject();
        update.put("create_time","2050-11-12 13:00:00");
        update.put("name","zjy");
        JSONObject where = new JSONObject();
        where.put("id",1);
        where.put("create_time","2017-01-17 15:56:53");
        mysqlData.setDb("test");
        mysqlData.setTable("app_antifraud");
        mysqlData.setInsert(insert);
        mysqlData.setUpdate(update);
        mysqlData.setWhere(where);
    }

    @Test
    public void testExecute(){
        System.out.println("123");
    }

}
