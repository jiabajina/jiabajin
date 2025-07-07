package wt.ssm.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 读者信息实体类，用于表示读者的基本信息
 * 实现Serializable接口以支持序列化
 */
public class ReaderInfo implements Serializable {

    /**
     * 读者ID，唯一标识一个读者
     */
    private long reader_id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 出生日期
     */
    private Date birth;
    /**
     * 地址
     */
    private String address;
    /**
     * 联系电话
     */
    private String phone;

    /**
     * 获取读者ID
     * @return 读者ID
     */
    public Long getReaderId() {
        return reader_id;
    }

    /**
     * 设置读者ID
     * @param readerId 读者ID
     */
    public void setReaderId(Long readerId) {
        this.reader_id = readerId;
    }

    /**
     * 获取姓名
     * @return 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性别
     * @return 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取出生日期
     * @return 出生日期
     */
    public Date getBirth() {
        return birth;
    }

    /**
     * 设置出生日期
     * @param birth 出生日期
     */
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    /**
     * 获取地址
     * @return 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取联系电话
     * @return 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系电话
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
