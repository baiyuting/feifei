package dao;

import entity.User;
import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户表处理
 * <p>
 * 根据 username 查询用户信息
 * select id,username,password from user where username=#{username};
 */
public class UserDAO {

    /**
     * 根据 username 查询用户信息
     * select id,username,password from user where username=#{username};
     *
     * @param username 查询的用户名
     * @return 查询的用户信息
     */
    public User findByUsername(String username) throws SQLException {
        DBUtil.getInstance().getConn();//建立连接
        List params = new ArrayList();
        params.add(username);
        ResultSet query = DBUtil.getInstance().query("select id,username,password from user where username=?", params);
        query.next();//光标移到第一行
        User user = new User();
        user.setId(query.getInt(1));
        user.setUsername(query.getString(2));
        user.setPassword(query.getString(3));
        DBUtil.getInstance().close();//关闭连接
        return user;
    }
}
