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
}
