package com.fancy.automation.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by puhui on 2017/1/10.
 */
public class MySqlDao {
    Logger logger = LoggerFactory.getLogger(MySqlDao.class);
    Connection conn = null;
    protected static final Properties properties = new Properties();
    static {
        try {
            properties.load(ClassLoader.getSystemResourceAsStream("db/db.conf/mysql.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MySqlDao(String db) {
        //加载配置文件
        String url = properties.getProperty("url")+"/"+db;
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        String driverClass = properties.getProperty("driverClass");
        logger.info("The configuration file loading is successful");
        logger.info("try to connect to the mysql db {}",url);
        Connection conn = null;
        try {
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url,username,password);
            logger.info("mysql database connection is successful {}",url);
        } catch (SQLException e) {
            logger.error("mysql database connection failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //释放数据库资源
    public static void releaseDB(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**执行sql语句,可以执行insert，update,delete*/
    private ResultSet execSql(String sql){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
