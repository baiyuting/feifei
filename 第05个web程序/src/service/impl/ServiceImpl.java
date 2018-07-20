package service.impl;

import dao.GoodsDAO;
import dao.UserDAO;
import entity.Goods;
import entity.User;
import service.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceImpl implements Service {

    // userDAO user表处理
    private UserDAO userDAO = new UserDAO();

    //goodsDAO goods表处理
    private GoodsDAO goodsDAO = new GoodsDAO();

    @Override
    public User verifyUser(String username, String password, String sessionVerifyCode, String userVerifyCode) {
        if (null != username && !"".equals(username.trim()) && null != password && !"".equals(password.trim()) &&
                null != sessionVerifyCode && !"".equals(sessionVerifyCode) && null != userVerifyCode && !"".equals(userVerifyCode) &&
                sessionVerifyCode.equals(userVerifyCode)) {//如果 username, password, sessionVerifyCode, userVerifyCode 非空，并且 验证码比对正确
            try {
                User user = userDAO.findByUsername(username);//通过数据库 按照 username 查询 user 信息
                if (password.equals(user.getPassword()))//如果数据库中查询 user 的 password 和 用户输入的 password 一致，返回 user 信息
                    return user;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Map<String, Object> getPage(String sPageNo, String sPageSize, String name) throws SQLException {
        Map<String, Object> map = new HashMap<>();
        int pageNo = null != sPageNo && sPageNo.matches("[1-9]\\d*") ? Integer.parseInt(sPageNo) : 1;//页号信息，如果不是数字，设为1
        map.put("pageNo", pageNo);
        int pageSize = null != sPageSize && sPageSize.matches("[1-9]\\d*") ? Integer.parseInt(sPageSize) : 10;//页面大小信息，如果不是数字，设为10
        map.put("pageSize", pageSize);
        map.put("list", goodsDAO.getListByName((pageNo - 1) * pageSize, pageSize, name));//获取 商品列表信息
        map.put("count", goodsDAO.count(name));//统计总数
        return map;
    }
}
