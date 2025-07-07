package wt.ssm.bean;

/**
 * 管理员实体类，用于表示系统管理员的基本信息
 */
public class Admin {

    /**
     * 管理员ID，唯一标识一个管理员
     */
    private long admin_id;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 用户名
     */
    private String username;

    /**
     * 获取管理员ID
     * @return 管理员ID
     */
    public long getAdminId() {
        return admin_id;
    }

    /**
     * 设置管理员ID
     * @param admin_id 管理员ID
     */
    public void setAdminId(long admin_id) {
        this.admin_id = admin_id;
    }

    /**
     * 获取登录密码
     * @return 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置登录密码
     * @param password 登录密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户名
     * @return 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
