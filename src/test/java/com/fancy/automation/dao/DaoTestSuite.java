package com.fancy.automation.dao;

import com.fancy.automation.model.MysqlDataTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by puhui on 2017/1/20.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({MysqlDataTest.class, MySqlDaoTest.class}
)
public class DaoTestSuite {
}
