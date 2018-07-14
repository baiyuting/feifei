package test;

import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestConnection {

    public static void main(String[] args) throws SQLException {
        DBUtil.getInstance().getConn();
        ResultSet resultSet = DBUtil.getInstance().query("select * from user where username='name'", null);
        resultSet.next();
        String username = resultSet.getString(2);
        String password = resultSet.getString(3);
        System.out.println(username + ":" + password);
        DBUtil.getInstance().close();
    }
}
