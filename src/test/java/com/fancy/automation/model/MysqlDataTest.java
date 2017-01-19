package com.fancy.automation.model;


import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by puhui on 2017/1/19.
 */
public class MysqlDataTest {

    private  MysqlData mysqlData = new MysqlData();

    @BeforeTest
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
    }


    @Test
    public void testToInsertSql(){
        String expect = "INSERT INTO app_antifraud ('create_time','name') values ('2050-11-12 13:00:00','nrf')";
        String actual = mysqlData.toInsertSql();
        System.out.println(actual);
        Assert.assertEquals(actual.toLowerCase(),expect.toLowerCase(),"ok");
    }

    @Test
    public void testToUpdateSql(){
        String expect = "update app_antifraud set 'create_time'='2050-11-12 13:00:00' and 'name'='zjy'" +
                " where 'id'=1 and 'create_time','2017-01-17 15:56:53'";
        String actual = mysqlData.toUpdateSql();
        Assert.assertEquals(actual.toLowerCase(),expect.toLowerCase(),"cuole");
    }
}
