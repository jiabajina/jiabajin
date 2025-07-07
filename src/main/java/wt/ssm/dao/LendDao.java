package wt.ssm.dao;

import wt.ssm.bean.Lend;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 借阅记录数据访问对象，负责图书借阅和归还相关的数据库操作
 */
@Repository
public class LendDao {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    private final static String NAMESPACE = "wt.ssm.dao.LendDao.";

    /**
     * 处理图书归还操作（更新借阅记录状态）
     * @param book_id 图书ID
     * @param reader_id 读者ID
     * @return 受影响的行数，大于0表示更新成功
     */
    public int returnBookOne(final long book_id, long reader_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("book_id", book_id);
        map.put("reader_id", reader_id);
        return sqlSessionTemplate.update(NAMESPACE + "returnBookOne", map);
    }

    /**
     * 处理图书归还操作（更新图书可借状态）
     * @param book_id 图书ID
     * @return 受影响的行数，大于0表示更新成功
     */
    public int returnBookTwo(final long book_id) {
        return sqlSessionTemplate.update(NAMESPACE + "returnBookTwo", book_id);
    }

    /**
     * 处理图书借阅操作（添加借阅记录）
     * @param book_id 图书ID
     * @param reader_id 读者ID
     * @return 受影响的行数，大于0表示添加成功
     */
    public int lendBookOne(final long book_id, final long reader_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("book_id", book_id);
        map.put("reader_id", reader_id);
        return sqlSessionTemplate.insert(NAMESPACE + "lendBookOne", map);
    }

    /**
     * 处理图书借阅操作（更新图书可借状态）
     * @param book_id 图书ID
     * @return 受影响的行数，大于0表示更新成功
     */
    public int lendBookTwo(final long book_id) {
        return sqlSessionTemplate.update(NAMESPACE + "lendBookTwo", book_id);
    }

    /**
     * 获取所有借阅记录
     * @return 借阅记录列表
     */
    public ArrayList<Lend> lendList() {
        List<Lend> result = sqlSessionTemplate.selectList(NAMESPACE + "lendList");
        return (ArrayList<Lend>) result;
    }

    /**
     * 获取管理员视图的借阅记录总数
     * @return 借阅记录总数
     */
    public int getAdminLendCount() {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getAdminLendCount");
    }

    /**
     * 分页获取管理员视图的借阅记录列表
     * @param start 起始位置
     * @param pageSize 每页记录数
     * @return 分页后的借阅记录列表
     */
    public List<Lend> getAdminLendListByPage(int start, int pageSize) {
        Map<String, Integer> params = new HashMap<>();
        params.put("start", start);
        params.put("pageSize", pageSize);
        return sqlSessionTemplate.selectList(NAMESPACE + "getAdminLendListByPage", params);
    }

    /**
     * 获取指定读者的借阅记录
     * @param reader_id 读者ID
     * @return 读者的借阅记录列表
     */
    public ArrayList<Lend> myLendList(final long reader_id) {
        List<Lend> result = sqlSessionTemplate.selectList(NAMESPACE + "myLendList", reader_id);
        return (ArrayList<Lend>) result;
    }

    /**
     * 获取读者借阅记录总数
     * @param reader_id 读者ID
     * @return 借阅记录总数
     */
    public int getMyLendCount(final long reader_id) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getMyLendCount", reader_id);
    }

    /**
     * 分页获取读者借阅记录
     * @param reader_id 读者ID
     * @param offset 起始偏移量
     * @param pageSize 每页记录数
     * @return 分页借阅记录列表
     */
    public List<Lend> getMyLendListByPage(final long reader_id, final int offset, final int pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("reader_id", reader_id);
        map.put("offset", offset);
        map.put("pageSize", pageSize);
        return sqlSessionTemplate.selectList(NAMESPACE + "getMyLendListByPage", map);
    }

    /**
     * 删除借阅记录
     * @param ser_num 借阅记录编号
     * @return 受影响的行数，大于0表示删除成功
     */
    public int deleteLend(final long ser_num) {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteLend", ser_num);
    }
}
