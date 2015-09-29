package com.xiongyingqi.jdbc.driver.impl;

import com.xiongyingqi.jdbc.config.JdbcConfig;
import com.xiongyingqi.jdbc.driver.DriverAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="http://xiongyingqi.com">qi</a>
 * @version 2015-09-29 17:25
 */
public class MySqlDriver implements DriverAdapter {
    private static final Logger logger = LoggerFactory.getLogger(MySqlDriver.class);
    public String driverClassName() {
        return "com.mysql.jdbc.Driver";
    }

    public Class diverClass() {
        try {
            Class<?> aClass = Class.forName(driverClassName());
            return aClass;
        } catch (ClassNotFoundException e) {
            logger.error("", e);
        }
        return null;
    }

    public String supportName() {
        return "mysql";
    }

    public String url(JdbcConfig jdbcConfig) {
        return "jdbc:mysql://" + jdbcConfig.getHost() + ":" + jdbcConfig.getPort() + "/" + jdbcConfig.getDatabase();
    }
}
