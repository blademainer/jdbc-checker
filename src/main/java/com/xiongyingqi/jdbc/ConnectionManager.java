package com.xiongyingqi.jdbc;

import com.xiongyingqi.jdbc.config.JdbcConfig;
import com.xiongyingqi.jdbc.driver.DriverAdapter;
import com.xiongyingqi.jdbc.driver.DriverAdapterManager;
import com.xiongyingqi.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author <a href="http://xiongyingqi.com">qi</a>
 * @version 2015-09-29 17:23
 */
public class ConnectionManager {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);

    public Connection createConnection(JdbcConfig jdbcConfig) {
        DriverAdapter driverAdapter = DriverAdapterManager.getDriverAdapter(jdbcConfig.getName());
        Assert.notNull(driverAdapter, "driverAdapter must not be null!");
        try {
            Class.forName(driverAdapter.driverClassName());
        } catch (ClassNotFoundException e) {
            logger.error("", e);
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(driverAdapter.url(jdbcConfig), jdbcConfig.getUserName(), jdbcConfig.getPassword());
        } catch (SQLException e) {
            logger.error("", e);
        }
        return connection;
    }

}
