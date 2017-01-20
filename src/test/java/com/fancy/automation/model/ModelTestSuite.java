package com.fancy.automation.model;

import com.fancy.automation.dao.MySqlDaoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by puhui on 2017/1/20.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({MysqlDataTest.class, MySqlDaoTest.class}
)
public class ModelTestSuite {

}