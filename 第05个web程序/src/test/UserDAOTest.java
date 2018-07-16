package test;

import dao.UserDAO;
import entity.User;

import java.sql.SQLException;

public class UserDAOTest {

    public static void main(String[] args) throws SQLException {
        User user = new UserDAO().findByUsername("name");
        System.out.println(user);
    }
}
