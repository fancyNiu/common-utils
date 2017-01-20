package com.fancy.automation;

import com.fancy.automation.dao.DaoTestSuite;
import com.fancy.automation.dao.MySqlDaoTest;
import com.fancy.automation.model.ModelTestSuite;
import com.fancy.automation.model.MysqlDataTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by puhui on 2017/1/20.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({DaoTestSuite.class, ModelTestSuite.class}
)
public class RunAll {
}




