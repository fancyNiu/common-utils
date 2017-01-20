package com.fancy.automation.model;


import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by puhui on 2017/1/19.
 */
public class MysqlDataTest {

    private  MysqlData mysqlData = new MysqlData();

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
    public void testToInsertSql(){
        String expect = "INSERT INTO app_antifraud ('create_time','name') values ('2050-11-12 13:00:00','nrf')";
        String actual = mysqlData.toInsertSql();
        System.out.println(actual);
        Assert.assertEquals("cuole",expect.toLowerCase(),actual.toLowerCase());
    }

    @Test
    public void testToUpdateSql(){
        String expect = "update app_antifraud set 'create_time' = '2050-11-12 13:00:00' and 'name' = 'zjy'" +
                " where 'create_time' = '2017-01-17 15:56:53' and 'id' = '1'";
        String actual = mysqlData.toUpdateSql();
        Assert.assertEquals("cuole",expect.toLowerCase(),actual.toLowerCase());
    }

    @Test
    public void testToDeleteSql(){
        String expect = "delete from app_antifraud where 'create_time' = '2017-01-17 15:56:53' and 'id' = '1'";
        String actual = mysqlData.toDeleteSql();
        Assert.assertEquals("cuole",expect.toLowerCase(),actual.toLowerCase());
    }
}
