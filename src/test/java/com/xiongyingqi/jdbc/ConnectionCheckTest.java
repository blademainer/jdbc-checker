package com.xiongyingqi.jdbc;

import com.xiongyingqi.jdbc.config.JdbcConfig;
import com.xiongyingqi.jdbc.driver.impl.HsqlDbDriver;
import com.xiongyingqi.jdbc.driver.impl.MySqlDriver;
import org.junit.Before;
import org.junit.Test;
import org.killbill.commons.embeddeddb.mysql.MySQLEmbeddedDB;

import java.net.URI;

/**
 * @author <a href="http://xiongyingqi.com">qi</a>
 * @version 2015-09-30 11:12
 */
public class ConnectionCheckTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testCheck() throws Exception {
        JdbcConfig jdbcConfig = new JdbcConfig();
        String name = new HsqlDbDriver().supportName();
        jdbcConfig.setName(name);
        ConnectionCheck connectionCheck = new ConnectionCheck(jdbcConfig);
        connectionCheck.check();
    }

    @Test
    public void testCheckMysql() throws Exception {
        MySQLEmbeddedDB db = new MySQLEmbeddedDB("test", "root", "", true);
        db.initialize();
        db.start();

        String jdbcConnectionString = db.getJdbcConnectionString();
        int port = URI.create(jdbcConnectionString.substring(5)).getPort();
//        MySQLStandaloneDB db = new MySQLStandaloneDB("test");
//        db.initialize();
//        db.start();

        System.out.println(jdbcConnectionString);

        JdbcConfig jdbcConfig = new JdbcConfig();
        String name = new MySqlDriver().supportName();
        jdbcConfig.setName(name);
        jdbcConfig.setHost("127.0.0.1");
        jdbcConfig.setDatabase("test");
        jdbcConfig.setUserName("root");
        jdbcConfig.setPassword("");
        jdbcConfig.setEncode("latin");
        jdbcConfig.setPort(port);
        ConnectionCheck connectionCheck = new ConnectionCheck(jdbcConfig);
        connectionCheck.check();
    }
    
}
