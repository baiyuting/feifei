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
 * <p>
 * ThreadLocal
 * 具有 set() 和 get() 方法，通过 set 方法可以设置 一个值，通过 get 方法可以获取设置的值，但是与线程相关，在线程1中 set 的值，只能在线程1中通过 get 方法才能得到，其他线程无法得到
 * 比如
 * 线程1： threadLocal.set(2)
 * 线程2： threadLocal.set(3)
 * 只有在线程1中调用 threadLocal.get 方法才能够得到 2
 * 在线程2中调用 threadLocal.get 方法返回 3
 */
public class DBUtil {

    private final static DBUtil INSTANCE = new DBUtil();//这个 通过 final 和 static 声明 为常量，内部实例化好的 DBUtil 对象

    // ThreadLocal 类，分别设置 每个线程的 Connection，PreparedStatement，ResultSet
    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();//每个线程 设置自己的 Connection ，只有线程自己才能够获取
    private static ThreadLocal<PreparedStatement> preparedStatementThreadLocal = new ThreadLocal<>();//每个线程设置自己的 PreparedStatement ，只有线程自己才能够获取
    private static ThreadLocal<ResultSet> resultSetThreadLocal = new ThreadLocal<>();//每个线程获取自己的 ResultSet ，只有线程自己才能够获取

    /**
     * 构造单例，所以使用 private 方法修饰构造函数，外面无法使用 new DBUtil() 方法
     */
    private DBUtil() {
    }

    /**
     * 通过 getInstance 方法获取 DBUtil 已经初始化好的实例，只有这一个实例
     *
     * @return 常量 INSTANCE
     */
    public static DBUtil getInstance() {
        return INSTANCE;
    }

    private String className = "com.mysql.jdbc.Driver";// 设置 jdbc 需要连接的驱动名，这儿设置的是 mysql Driver，肥肥要设置成为 oracle 的 Driver
    private String url = "jdbc:mysql://localhost:3306/feifei";// 这儿是 mysql 的链接 url ，老哥连接的是 本地的 mysql ，数据库名字叫做 feifei，老肥要连接自己的数据库
    private String user = "root";//这儿是老哥 mysql 的连接用户名
    private String pwd = "1234";//这儿是老哥 mysql 的 密码

    /**
     * 获取当前连接，并将 连接 存放在 connectionThreadLocal 中
     *
     * @return 连接
     */
    public Connection getConn() {
        Connection conn = connectionThreadLocal.get();
        try {
            if (null == conn) {
                Class.forName(className);
                conn = DriverManager.getConnection(url, user, pwd);
                connectionThreadLocal.set(conn);//设置本线程对应的 conn
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 查询方法，调用此方法之前必须要 调用 getConn 方法建立建立连接
     *
     * @param sql    查询 sql 语句
     * @param params 条件参数
     * @return rs
     */
    public ResultSet query(String sql, List<Object> params) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = preparedStatementThreadLocal.get();
            if (null == ps) {
                ps = connectionThreadLocal.get().prepareStatement(sql);
                preparedStatementThreadLocal.set(ps);//设置本线程对应的 ps
            }
            if (params != null && params.size() > 0) {
                for (int i = 0; i < params.size(); i++) {
                    ps.setObject(i + 1, params.get(i));
                }
            }
            rs = ps.executeQuery();
            resultSetThreadLocal.set(rs);//设置本线程对应的 rs
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 增，删，改 操作
     *
     * @param sql    增，删，改 sql 语句
     * @param params 设置的 参数
     */
    public void update(String sql, List<Object> params) {
        try {
            PreparedStatement ps = connectionThreadLocal.get().prepareStatement(sql);
            if (params != null && params.size() > 0) {
                for (int i = 0; i < params.size(); i++) {
                    ps.setObject(i + 1, params.get(i));
                }
            }
            ps.executeUpdate();
            preparedStatementThreadLocal.set(ps);//设置本线程对应的 ps
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭 连接
     * <p>
     * 会将当前线程中打开的所有连接关闭，主要 ResultSet,PreparedStatement,Connection 三个变量
     */
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
