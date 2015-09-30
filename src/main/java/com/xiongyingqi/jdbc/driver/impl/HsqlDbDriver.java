package com.xiongyingqi.jdbc.driver.impl;

import com.xiongyingqi.jdbc.config.JdbcConfig;
import com.xiongyingqi.jdbc.driver.DriverAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="http://xiongyingqi.com">qi</a>
 * @version 2015-09-30 10:37
 */
public class HsqlDbDriver implements DriverAdapter {
    private static final Logger logger = LoggerFactory.getLogger(HsqlDbDriver.class);
    @Override
    public String driverClassName() {
        return "org.hsqldb.jdbcDriver";
    }

    @Override
    public Class diverClass() {
        try {
            return Class.forName(driverClassName());
        } catch (ClassNotFoundException e) {
            logger.error("", e);
        }
        return null;
    }

    @Override
    public String supportName() {
        return "hsqldb";
    }

    @Override
    public String url(JdbcConfig jdbcConfig) {
        return "jdbc:hsqldb:mem:" + jdbcConfig.getDatabase();
    }
}
