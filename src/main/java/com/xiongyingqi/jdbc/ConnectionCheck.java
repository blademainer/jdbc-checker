package com.xiongyingqi.jdbc;

import com.xiongyingqi.jdbc.config.JdbcConfig;
import com.xiongyingqi.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author <a href="http://xiongyingqi.com">qi</a>
 * @version 2015-09-29 17:23
 */
public class ConnectionCheck {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionCheck.class);
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

        String sql = "CREATE TABLE " + tmpTableName + " (test_name VARCHAR(255))  DEFAULT CHAR SET utf8";
        Query query = new Query(connection);
        int update = query.update(sql);
        if (logger.isDebugEnabled()) {
            logger.debug("create table: {}, sql: {}, return size: {}", tmpTableName, sql, update);
        }

        String updateSql = "insert into " + tmpTableName + " values ('呵呵哒')";
        int update1 = query.update(updateSql);
        if (logger.isDebugEnabled()) {
            logger.debug("insert data... sql: {}, return size: {}", updateSql, update1);
        }

        String querySql = "select * from " + tmpTableName + " where test_name='呵呵哒'";
        ResultSet resultSet = query.query(querySql);
        if (logger.isDebugEnabled()) {
            logger.debug("insert data... sql: {}, return resultSet: {}", querySql, resultSet);
        }
        try {
//            while (resultSet.next()) {
//                String testName = resultSet.getString("test_name");
//                System.out.println(testName);
//            }
            resultSet.last();
            int rowCount = resultSet.getRow();
            Assert.equals(1, rowCount, "query error!");
        } catch (SQLException e) {
            logger.error("", e);
        }

        String dropTableSql = "drop table " + tmpTableName;
        int update2 = query.update(dropTableSql);
        if (logger.isDebugEnabled()) {
            logger.debug("drop table... sql: {}, return size: {}", dropTableSql, update2);
        }
        Assert.equals(0, update2, "drop table error!");
    }
}
