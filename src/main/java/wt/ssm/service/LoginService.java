package wt.ssm.service;

import wt.ssm.bean.ReaderCard;
import wt.ssm.dao.AdminDao;
import wt.ssm.dao.ReaderCardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录服务类，处理管理员和读者的登录验证及密码管理业务逻辑
 */
@Service
public class LoginService {

    @Autowired
    private ReaderCardDao readerCardDao;
    @Autowired
    private AdminDao adminDao;

    /**
     * 验证读者账号密码是否匹配
     * @param readerId 读者ID
     * @param password 读者密码
     * @return 匹配成功返回true，否则返回false
     */
    public boolean hasMatchReader(long readerId,String password){
        return  readerCardDao.getIdMatchCount(readerId, password)>0;
    }

    /**
     * 根据管理员ID获取用户名
     * @param adminId 管理员ID
     * @return 管理员用户名
     */
    public String getAdminUsername(long adminId) {
        return adminDao.getUsername(adminId);
    }

    /**
     * 根据读者ID查找读者借阅卡信息
     * @param readerId 读者ID
     * @return 对应的ReaderCard对象，若不存在则返回null
     */
    public ReaderCard findReaderCardByReaderId(long readerId){
        return readerCardDao.findReaderByReaderId(readerId);
    }

    /**
     * 验证管理员账号密码是否匹配
     * @param adminId 管理员ID
     * @param password 管理员密码
     * @return 匹配成功返回true，否则返回false
     */
    public boolean hasMatchAdmin(long adminId,String password){
        return adminDao.getMatchCount(adminId, password) == 1;
    }

    /**
     * 重置管理员密码
     * @param adminId 管理员ID
     * @param newPassword 新密码
     * @return 重置成功返回true，否则返回false
     */
    public boolean adminRePassword(long adminId, String newPassword){
        return adminDao.resetPassword(adminId,newPassword)>0;
    }

    /**
     * 获取管理员当前密码
     * @param adminId 管理员ID
     * @return 管理员当前密码
     */
    public String getAdminPassword(long adminId){
        return adminDao.getPassword(adminId);
    }

    /**
     * 重置读者密码
     * @param readerId 读者ID
     * @param newPassword 新密码
     * @return 重置成功返回true，否则返回false
     */
    public boolean readerRePassword(long readerId, String newPassword) {
        return readerCardDao.resetPassword(readerId, newPassword) > 0;
    }

    /**
     * 获取读者当前密码
     * @param readerId 读者ID
     * @return 读者当前密码
     */
    public String getReaderPassword(long readerId) {
        return readerCardDao.getPassword(readerId);
    }


}
