package wt.ssm.dao;

import wt.ssm.bean.ReaderInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读者信息数据访问对象，负责读者信息相关的数据库操作
 */
@Repository
public class ReaderInfoDao {

    private final static String NAMESPACE = "wt.ssm.dao.ReaderInfoDao.";
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 获取所有读者信息
     * @return 读者信息列表
     */
    public ArrayList<ReaderInfo> getAllReaderInfo() {
        List<ReaderInfo> result = sqlSessionTemplate.selectList(NAMESPACE + "getAllReaderInfo");
        return (ArrayList<ReaderInfo>) result;
    }

    /**
     * 根据读者ID查找读者信息
     * @param reader_id 读者ID
     * @return 对应的ReaderInfo对象，若不存在则返回null
     */
    public ReaderInfo findReaderInfoByReaderId(final long reader_id) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "findReaderInfoByReaderId", reader_id);
    }

    /**
     * 删除读者信息
     * @param reader_id 读者ID
     * @return 受影响的行数，大于0表示删除成功
     */
    public int deleteReaderInfo(final long reader_id) {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteReaderInfo", reader_id);
    }

    /**
     * 编辑读者信息
     * @param readerInfo 读者信息对象
     * @return 受影响的行数，大于0表示更新成功
     */
    public int editReaderInfo(final ReaderInfo readerInfo) {
        return sqlSessionTemplate.update(NAMESPACE + "editReaderInfo", readerInfo);
    }

    /**
     * 编辑读者借阅卡信息
     * @param readerInfo 读者信息对象
     * @return 受影响的行数，大于0表示更新成功
     */
    public int editReaderCard(final ReaderInfo readerInfo) {
        return sqlSessionTemplate.update(NAMESPACE + "editReaderCard", readerInfo);
    }

    /**
     * 添加读者信息
     * @param readerInfo 读者信息对象
     * @return 添加成功返回新读者ID，失败返回-1
     */
    public final Long addReaderInfo(final ReaderInfo readerInfo) {
        int rows = sqlSessionTemplate.insert(NAMESPACE + "addReaderInfo", readerInfo);
        if (rows > 0) {
            return readerInfo.getReaderId();
        } else {
            return -1L;
        }
    }

    /**
     * 分页和模糊查询读者信息
     * @param offset 起始位置
     * @param pageSize 每页条数
     * @param name 姓名模糊查询
     * @return 读者信息列表
     */
    public List<ReaderInfo> getReaderInfoByPageAndName(int offset, int pageSize, String name) {
        Map<String, Object> param = new HashMap<>();
        param.put("offset", offset);
        param.put("pageSize", pageSize);
        param.put("name", name);
        List<ReaderInfo> result = sqlSessionTemplate.selectList(NAMESPACE + "getReaderInfoByPageAndName", param);
        return result;
    }

    /**
     * 获取模糊查询后的总数
     * @param name 姓名模糊查询
     * @return 总数
     */
    public int getReaderInfoCountByName(String name) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getReaderInfoCountByName", name);
    }
}
