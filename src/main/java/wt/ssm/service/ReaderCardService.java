package wt.ssm.service;

import wt.ssm.bean.ReaderInfo;
import wt.ssm.dao.ReaderCardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 读者借阅卡服务类，处理读者借阅卡的创建、密码更新和删除等业务逻辑
 */
@Service
public class ReaderCardService {
    @Autowired
    private ReaderCardDao readerCardDao;

    /**
     * 添加读者借阅卡
     * @param readerInfo 读者信息对象
     * @param password 读者密码
     * @return 添加成功返回true，否则返回false
     */
    public boolean addReaderCard(ReaderInfo readerInfo, String password){
        return  readerCardDao.addReaderCard(readerInfo,password)>0;
    }

    /**
     * 更新读者密码
     * @param readerId 读者ID
     * @param password 新密码
     * @return 更新成功返回true，否则返回false
     */
    public boolean updatePassword(long readerId, String password){
        return readerCardDao.resetPassword(readerId,password)>0;
    }

    /**
     * 删除读者借阅卡
     * @param readerId 读者ID
     * @return 删除成功返回true，否则返回false
     */
    public boolean deleteReaderCard(long readerId) {
        return readerCardDao.deleteReaderCard(readerId) > 0;
    }
}
