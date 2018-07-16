package service;

import entity.User;

/**
 * 作为 后台商品管理系统 Service
 * <p>
 * 1、验证用户登陆信息 username password
 */
public interface Service {

    /**
     * 验证用户名和密码
     * 本方法中 会 根据 username 查询用户信息，
     * 比对 数据库中 的 password 和 用户输入的 password 是否一致，
     * 一致返回查询用户信息，否则返回 null
     *
     * @param username 用户名
     * @param password 密码
     * @return 如果用户名密码正确，返回 查询用户，如果不正确，返回 null
     */
    User verifyUser(String username, String password);
}
