package com.xiongyingqi.jdbc.driver;

import com.xiongyingqi.jdbc.config.JdbcConfig;

/**
 * @author <a href="http://xiongyingqi.com">qi</a>
 * @version 2015-09-29 17:24
 */
public interface DriverAdapter {
    String driverClassName();
    Class diverClass();
    String supportName();
    String url(JdbcConfig jdbcConfig);
}
