package wt.ssm.service;

import wt.ssm.bean.ClassInfo;
import wt.ssm.dao.ClassInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 图书分类信息服务类，提供图书分类相关的业务操作
 */
@Service
public class ClassInfoService {
    @Autowired
    private ClassInfoDao classInfoDao;

    /**
     * 获取所有图书分类信息
     * @return 包含所有分类信息的列表
     */
    public List<ClassInfo> getAllClasses() {
        return classInfoDao.getAllClasses();
    }

    /**
     * 根据分类ID获取分类信息
     * @param classId 分类ID
     * @return 对应的分类信息对象，若不存在则返回null
     */
    public ClassInfo getClassInfoById(Long classId) {
        List<ClassInfo> all = classInfoDao.getAllClasses();
        for (ClassInfo c : all) {
            if (c.getClassId().equals(classId)) {
                return c;
            }
        }
        return null;
    }
}
