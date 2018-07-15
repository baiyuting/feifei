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
 * <p>
 * <p>
 * DBUtil 单例设计：
 * <p>
 * 构造方法 private ：
 * private DBUtil(){}
 * 这样的话就不能使用 new DBUtil() 来实例化
 * <p>
 * 同时提供
 * public DBUtil getInstance() 方法，用户只能通过这个方法获得实例
 * <p>
 * 在 getInstance() 方法里面返回已经 生成好的 INSTANCE 实例，这个 INSTANCE 是 常量
 */
public class DBUtil {

    private final static DBUtil INSTANCE = new DBUtil();//这个 通过 final 和 static 声明 为常量

    // ThreadLocal 类
    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<PreparedStatement> preparedStatementThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<ResultSet> resultSetThreadLocal = new ThreadLocal<>();

    private DBUtil() {
    }

    public static void main(String[] args) {
        getInstance().getConn();
    }

    public static DBUtil getInstance() {
        return INSTANCE;
    }

    private String className = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/feifei";
    private String user = "root";
    private String pwd = "1234";

    public Connection getConn() {
        Connection conn = connectionThreadLocal.get();
        try {
            if (null == conn) {
                Class.forName(className);
                conn = DriverManager.getConnection(url, user, pwd);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 查询
    public ResultSet query(String sql, List<Object> params) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = preparedStatementThreadLocal.get();
            if (null == ps) {
                ps = connectionThreadLocal.get().prepareStatement(sql);
                preparedStatementThreadLocal.set(ps);
            }
            if (params != null && params.size() > 0) {
                for (int i = 0; i < params.size(); i++) {
                    ps.setObject(i + 1, params.get(i));
                }
            }
            rs = ps.executeQuery();
            resultSetThreadLocal.set(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // 增，删，改
    public void update(String sql, List<Object> params) {
        try {
            PreparedStatement ps = connectionThreadLocal.get().prepareStatement(sql);
            if (params != null && params.size() > 0) {
                for (int i = 0; i < params.size(); i++) {
                    ps.setObject(i + 1, params.get(i));
                }
            }
            ps.executeUpdate();
            preparedStatementThreadLocal.set(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (resultSetThreadLocal.get() != null)
                resultSetThreadLocal.get().close();
            if (preparedStatementThreadLocal.get() != null)
                preparedStatementThreadLocal.get().close();
            if (null != connectionThreadLocal.get())
                connectionThreadLocal.get().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
