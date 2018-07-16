package entity;

/**
 * 用户表对应转化对象
 * <p>
 * 包含：
 * id
 * username 用户名（账号） 需要根据用户名查询用户信息，需要 唯一 并且 添加索引
 * password 密码
 */
public class User {

    private Integer id;
    private String username;//用户名（账号）
    private String password;//密码

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
