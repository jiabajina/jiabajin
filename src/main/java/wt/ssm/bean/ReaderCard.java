package wt.ssm.bean;

import java.io.Serializable;

/**
 * 读者借阅卡实体类，用于存储读者的登录信息和身份标识
 * 实现Serializable接口以支持序列化
 */
public class ReaderCard implements Serializable {

    /**
     * 读者ID，唯一标识一位读者
     */
    private long reader_id;
    /**
     * 读者用户名，用于登录系统
     */
    private String username;
    /**
     * 读者密码，用于登录系统验证
     */
    private String password;

    /**
     * 获取读者ID
     * @return 读者ID
     */
    public long getReaderId() {
        return reader_id;
    }

    /**
     * 设置读者ID
     * @param reader_id 读者ID
     */
    public void setReaderId(long reader_id) {
        this.reader_id = reader_id;
    }

    /**
     * 获取读者用户名
     * @return 读者用户名
     */
    public String getName() {
        return username;
    }

    /**
     * 设置读者用户名
     * @param username 读者用户名
     */
    public void setName(String username) {
        this.username = username;
    }

    /**
     * 获取读者密码
     * @return 读者密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置读者密码
     * @param password 读者密码
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
