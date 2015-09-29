package com.xiongyingqi.jdbc;

import com.xiongyingqi.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author <a href="http://xiongyingqi.com">qi</a>
 * @version 2015-09-29 18:00
 */
public class Query {
    private static final Logger logger = LoggerFactory.getLogger(Query.class);
    private Connection connection;

    public Query(Connection connection) {
        this.connection = connection;
    }

    public ResultSet query(String sql) {
        Assert.hasText(sql);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (logger.isDebugEnabled()) {
                logger.debug("execute update... sql: {}, resultSet: {}", sql, resultSet);
            }
            return resultSet;
        } catch (SQLException e) {
            logger.error("", e);
        }
        return null;
    }

    public int update(String sql) {
        Assert.hasText(sql);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int n = preparedStatement.executeUpdate();
            if (logger.isDebugEnabled()) {
                logger.debug("execute update... sql: {}, result: {}", sql, n);
            }
            return n;
        } catch (SQLException e) {
            logger.error("", e);
        }
        return -1;
    }


}
