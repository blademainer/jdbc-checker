package com.xiongyingqi.jdbc.config;

/**
 * @author <a href="http://xiongyingqi.com">qi</a>
 * @version 2015-09-29 18:20
 */
public class JdbcConfig {
    private String name;
    private String host;
    private Integer port;
    private String database;
    private String userName;
    private String password;
    private String encode;
    public static final String DEFAULT_ENCODING = "UTF8";

    public JdbcConfig() {
    }
    /**
     * 带参数初始化
     *  @param name     数据库名称，如mysql、oracle
     * @param host
     * @param port
     * @param database
     * @param userName
     * @param password
     * @param encode
     */
    public JdbcConfig(String name, String host, Integer port, String database, String userName, String password, String encode) {
        this.name = name;
        this.host = host;
        this.port = port;
        this.database = database;
        this.userName = userName;
        this.password = password;
        this.encode = encode;
    }




    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port == null ? 3306 : port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    @Override
    public String toString() {
        return "JdbcConfig{" +
                "name='" + name + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", database='" + database + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", encode='" + encode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JdbcConfig)) return false;

        JdbcConfig that = (JdbcConfig) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        if (port != null ? !port.equals(that.port) : that.port != null) return false;
        if (database != null ? !database.equals(that.database) : that.database != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        return !(encode != null ? !encode.equals(that.encode) : that.encode != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        result = 31 * result + (database != null ? database.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (encode != null ? encode.hashCode() : 0);
        return result;
    }
}
