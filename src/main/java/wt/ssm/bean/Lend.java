package wt.ssm.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 借阅记录实体类，用于表示图书借阅的相关信息
 * 实现Serializable接口以支持序列化
 */
public class Lend implements Serializable {

    /**
     * 借阅记录编号，唯一标识一条借阅记录
     */
    private long ser_num;
    /**
     * 图书ID，关联图书信息表
     */
    private long book_id;
    /**
     * 读者ID，关联读者信息表
     */
    private long reader_id;
    /**
     * 借阅日期
     */
    private Date lend_date;
    /**
     * 归还日期，null表示未归还
     */
    private Date back_date;

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
     * 获取图书ID
     * @return 图书ID
     */
    public long getBookId() {
        return book_id;
    }

    /**
     * 设置图书ID
     * @param book_id 图书ID
     */
    public void setBookId(long book_id) {
        this.book_id = book_id;
    }

    /**
     * 设置借阅记录编号
     * @param ser_num 借阅记录编号
     */
    public void setSer_num(long ser_num) {
        this.ser_num = ser_num;
    }

    /**
     * 获取归还日期
     * @return 归还日期，null表示未归还
     */
    public Date getBackDate() {
        return back_date;
    }

    /**
     * 设置归还日期
     * @param back_date 归还日期
     */
    public void setBackDate(Date back_date) {
        this.back_date = back_date;
    }

    /**
     * 获取借阅日期
     * @return 借阅日期
     */
    public Date getLendDate() {
        return lend_date;
    }

    /**
     * 设置借阅日期
     * @param lend_date 借阅日期
     */
    public void setLendDate(Date lend_date) {
        this.lend_date = lend_date;
    }

    /**
     * 获取借阅记录编号
     * @return 借阅记录编号
     */
    public long getSer_num() {
        return ser_num;
    }
}
