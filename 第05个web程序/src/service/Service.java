package service;

import entity.User;

import java.sql.SQLException;
import java.util.Map;

/**
 * 作为 后台商品管理系统 Service
 * <p>
 * 1、验证用户登陆信息 username password
 * 2、分页显示数据
 */
public interface Service {

    /**
     * 验证用户名和密码
     * 本方法中 会 根据 username 查询用户信息，
     * 比对 数据库中 的 password 和 用户输入的 password 是否一致，
     * 一致返回查询用户信息，否则返回 null
     * <p>
     * 本方法也会验证 用户输入验证码 是否和 服务端存储验证码 一致，如果不一致，说明用户输入验证码出错
     *
     * @param username          用户名
     * @param password          密码
     * @param sessionVerifyCode 服务器端保存的验证码
     * @param userVerifyCode    用户输入的验证码
     * @return 如果用户名密码正确，返回 查询用户，如果不正确，返回 null
     */
    User verifyUser(String username, String password, String sessionVerifyCode, String userVerifyCode);

    /**
     * 获取 商品分页信息，放置在一个 map 集合中， 包括：
     * 1、商品信息列表 返回 id,name,status -> List<Goods> items
     * 2、商品总数 count
     * 3、当前页码 pageNo
     * 4、页面大小 pageSize
     *
     * @param sPageNo   第几页，如果为 空 默认 为 1
     * @param sPageSize 页面大小，如果为空 默认 10
     * @param name      商品名称，如果为空，查询整个表记录
     * @return 分页信息，是一个 Map ，里面包含有分页信息
     */
    Map<String, Object> getPage(String sPageNo, String sPageSize, String name) throws SQLException;
}
