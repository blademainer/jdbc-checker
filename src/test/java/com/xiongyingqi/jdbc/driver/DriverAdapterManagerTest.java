package com.xiongyingqi.jdbc.driver;

import com.xiongyingqi.jdbc.driver.impl.MySqlDriver;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author <a href="http://xiongyingqi.com">qi</a>
 * @version 2015-09-29 18:41
 */
public class DriverAdapterManagerTest {

    @Test
    public void testGetDriverAdapter() throws Exception {
        DriverAdapter mysql = DriverAdapterManager.getDriverAdapter("mysql");
        Assert.assertEquals(MySqlDriver.class.getName(), mysql.getClass().getName());
    }
}
