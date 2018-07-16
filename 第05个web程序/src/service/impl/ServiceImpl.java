package service.impl;

import dao.UserDAO;
import entity.User;
import service.Service;

import java.sql.SQLException;

public class ServiceImpl implements Service {
    @Override
    public User verifyUser(String username, String password) {
        if (null != username && !"".equals(username.trim()) && null != password && !"".equals(password.trim())) {//如果 username 和 password 非空
            try {
                User user = new UserDAO().findByUsername(username);//通过数据库 按照 username 查询 user 信息
                if (password.equals(user.getPassword()))//如果数据库中查询 user 的 password 和 用户输入的 password 一致，返回 user 信息
                    return user;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
