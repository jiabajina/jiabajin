package wt.ssm.dao;

import wt.ssm.bean.ReaderCard;
import wt.ssm.bean.ReaderInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 读者借阅卡数据访问对象，负责读者借阅卡相关的数据库操作
 */
@Repository
public class ReaderCardDao {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    private final static String NAMESPACE = "wt.ssm.dao.ReaderCardDao.";

    /**
     * 验证读者ID和密码是否匹配
     * @param reader_id 读者ID
     * @param password 读者密码
     * @return 匹配的记录数，大于0表示匹配成功
     */
    public int getIdMatchCount(final long reader_id, final String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("reader_id", reader_id);
        map.put("password", password);
        return sqlSessionTemplate.selectOne(NAMESPACE + "getIdMatchCount", map);
    }

    /**
     * 根据读者ID查找读者借阅卡信息
     * @param reader_id 读者ID
     * @return 对应的ReaderCard对象，若不存在则返回null
     */
    public ReaderCard findReaderByReaderId(final long reader_id) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "findReaderByReaderId", reader_id);
    }

    /**
     * 重置读者密码
     * @param reader_id 读者ID
     * @param newPassword 新密码
     * @return 受影响的行数，大于0表示更新成功
     */
    public int resetPassword(final long reader_id, final String newPassword) {
        Map<String, Object> map = new HashMap<>();
        map.put("reader_id", reader_id);
        map.put("password", newPassword);
        return sqlSessionTemplate.update(NAMESPACE + "resetPassword", map);
    }

    /**
     * 添加读者借阅卡
     * @param readerInfo 读者信息对象
     * @param password 读者密码
     * @return 受影响的行数，大于0表示添加成功
     */
    public int addReaderCard(final ReaderInfo readerInfo, final String password) {
        String username = readerInfo.getName();
        long reader_id = readerInfo.getReaderId();
        Map<String, Object> map = new HashMap<>();
        map.put("reader_id", reader_id);
        map.put("username", username);
        map.put("password", password);
        return sqlSessionTemplate.insert(NAMESPACE + "addReaderCard", map);
    }

    /**
     * 获取读者密码
     * @param reader_id 读者ID
     * @return 读者的密码
     */
    public String getPassword(final long reader_id) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getPassword", reader_id);
    }

    /**
     * 删除读者借阅卡
     * @param reader_id 读者ID
     * @return 受影响的行数，大于0表示删除成功
     */
    public int deleteReaderCard(final long reader_id) {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteReaderCard", reader_id);
    }
}
