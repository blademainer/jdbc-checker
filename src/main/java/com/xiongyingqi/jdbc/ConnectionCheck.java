package com.xiongyingqi.jdbc;

import com.xiongyingqi.jdbc.config.JdbcConfig;
import com.xiongyingqi.util.Assert;

import java.sql.Connection;

/**
 * @author <a href="http://xiongyingqi.com">qi</a>
 * @version 2015-09-29 17:23
 */
public class ConnectionCheck {
    private JdbcConfig jdbcConfig;

    public ConnectionCheck() {

    }

    public ConnectionCheck(JdbcConfig jdbcConfig) {
        this.jdbcConfig = jdbcConfig;
    }

    public void check() {
        Assert.notNull(jdbcConfig, "must init jdbcConfig");
        String tmpTableName = "jdbc_check_" + System.currentTimeMillis();
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.createConnection(jdbcConfig);
        String sql = "CREATE TABLE " + tmpTableName + " (test_name VARCHAR(255))";
        Query query = new Query(connection);
        int update = query.update(sql);
        System.out.println(update);
    }
}
