package wt.ssm.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员数据访问对象，负责管理员相关的数据库操作
 */
@Repository
public class AdminDao {

    private final static String NAMESPACE = "wt.ssm.dao.AdminDao.";
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 验证管理员ID和密码是否匹配
     * @param admin_id 管理员ID
     * @param password 管理员密码
     * @return 匹配的记录数，1表示匹配成功，0表示匹配失败
     */
    public int getMatchCount(final long admin_id, final String password) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("admin_id", admin_id);
        paramMap.put("password", password);
        return sqlSessionTemplate.selectOne(NAMESPACE + "getMatchCount", paramMap);
    }

    /**
     * 重置管理员密码
     * @param admin_id 管理员ID
     * @param password 新密码
     * @return 受影响的行数，大于0表示更新成功
     */
    public int resetPassword(final long admin_id, final String password) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("admin_id", admin_id);
        paramMap.put("password", password);
        return sqlSessionTemplate.update(NAMESPACE + "resetPassword", paramMap);
    }

    /**
     * 获取管理员密码
     * @param admin_id 管理员ID
     * @return 管理员的密码
     */
    public String getPassword(final long admin_id) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getPassword", admin_id);
    }

    /**
     * 获取管理员用户名
     * @param admin_id 管理员ID
     * @return 管理员的用户名
     */
    public String getUsername(final long admin_id) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getUsername", admin_id);
    }

}
