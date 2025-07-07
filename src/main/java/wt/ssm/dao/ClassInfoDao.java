package wt.ssm.dao;

import wt.ssm.bean.ClassInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * 图书分类数据访问对象，负责图书分类相关的数据库操作
 */
@Repository
public class ClassInfoDao {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    private final static String NAMESPACE = "wt.ssm.dao.ClassInfoDao.";

    /**
     * 获取所有图书分类信息
     * @return 包含所有分类信息的列表
     */
    public List<ClassInfo> getAllClasses() {
        return sqlSessionTemplate.selectList(NAMESPACE + "getAllClasses");
    }

    /**
     * 根据分类ID获取分类信息
     * @param classId 分类ID
     * @return 分类信息对象
     */
    public ClassInfo getClassInfoById(Long classId) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getClassInfoById", classId);
    }

    /**
     * 添加新分类
     * @param classInfo 分类信息对象
     * @return 受影响的行数
     */
    public int addClass(ClassInfo classInfo) {
        return sqlSessionTemplate.insert(NAMESPACE + "addClass", classInfo);
    }

    /**
     * 更新分类信息
     * @param classInfo 分类信息对象
     * @return 受影响的行数
     */
    public int updateClass(ClassInfo classInfo) {
        return sqlSessionTemplate.update(NAMESPACE + "updateClass", classInfo);
    }

    /**
     * 删除分类
     * @param classId 分类ID
     * @return 受影响的行数
     */
    public int deleteClass(Long classId) {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteClass", classId);
    }

    /**
     * 获取分类总数
     * @return 分类总数
     */
    public int getClassCount() {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getClassCount");
    }

    /**
     * 分页获取分类列表
     * @param offset 起始偏移量
     * @param pageSize 每页大小
     * @return 分类列表
     */
    public List<ClassInfo> getClassListByPage(int offset, int pageSize) {
        Map<String, Integer> params = new HashMap<>();
        params.put("offset", offset);
        params.put("pageSize", pageSize);
        return sqlSessionTemplate.selectList(NAMESPACE + "getClassListByPage", params);
    }
}


