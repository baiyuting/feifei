package util;

import java.sql.*;
import java.util.List;

/**
 * 数据库连接工具
 * <p>
 * 这是连接这边电脑 msyql 数据库的相关配置，同时需要添加 mysql 连接的 jar 包 mysql-connector-java-5.1.6-bin.jar
 * <p>
 * private String className = "com.mysql.jdbc.Driver";
 * private String url = "jdbc:mysql://localhost:3306/feifei";
 * private String user = "root";
 * private String pwd = "1234";
 * <p>
 * <p>
 * 如果是肥肥要连接，需要更改这些配置，同时需要添加 oracle 连接的 jar 包
 * 可能如下：
 * <p>
 * final String DRIVER = "oracle.jdbc.driver.OracleDriver";
 * final String URL = "jdbc:oracle:thin:@localhost:1521:mldn";
 * final String USER = "scott";
 * final String PASSWORD = "tiger";
 */
public class DBUtil {

    private final static DBUtil util = new DBUtil();

    private DBUtil() {

    }

    public static void main(String[] args) {
        getInstance().getConn();
    }

    public static DBUtil getInstance() {
        return util;
    }

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    private String className = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/feifei";
    private String user = "root";
    private String pwd = "1234";

    public Connection getConn() {
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 查询
    public ResultSet query(String sql, List<Object> params) {
        try {
            ps = conn.prepareStatement(sql);
            if (params != null && params.size() > 0) {
                for (int i = 0; i < params.size(); i++) {
                    ps.setObject(i + 1, params.get(i));
                }
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // 增，删，改
    public void update(String sql, List<Object> params) {
        try {
            ps = conn.prepareStatement(sql);
            if (params != null && params.size() > 0) {
                for (int i = 0; i < params.size(); i++) {
                    ps.setObject(i + 1, params.get(i));
                }
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (rs == null) {
                rs.close();
            }
            if (ps == null) {
                ps.close();
            }
            if (conn == null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
