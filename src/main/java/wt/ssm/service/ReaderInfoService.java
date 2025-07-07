package wt.ssm.service;

import wt.ssm.bean.ReaderInfo;
import wt.ssm.dao.ReaderInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 读者信息服务类，处理读者信息的查询、添加、编辑和删除等业务逻辑
 */
@Service
public class ReaderInfoService {
    @Autowired
    private ReaderInfoDao readerInfoDao;
    @Autowired
    private ReaderCardService readerCardService;

    /**
     * 获取所有读者信息
     * @return 包含所有读者信息的ArrayList集合
     */
    public ArrayList<ReaderInfo> readerInfos() {
        return readerInfoDao.getAllReaderInfo();
    }

    /**
     * 删除读者信息
     * @param readerId 读者ID
     * @return 删除成功返回true，否则返回false
     */
    public boolean deleteReaderInfo(long readerId) {
        return readerInfoDao.deleteReaderInfo(readerId) > 0;
    }

    /**
     * 根据读者ID获取读者信息
     * @param readerId 读者ID
     * @return 对应的ReaderInfo对象，若不存在则返回null
     */
    public ReaderInfo getReaderInfo(long readerId) {
        return readerInfoDao.findReaderInfoByReaderId(readerId);
    }

    /**
     * 编辑读者信息
     * @param readerInfo 包含更新信息的ReaderInfo对象
     * @return 编辑成功返回true，否则返回false
     */
    public boolean editReaderInfo(ReaderInfo readerInfo) {
        return readerInfoDao.editReaderInfo(readerInfo) > 0;
    }

    /**
     * 编辑读者借阅卡信息
     * @param readerInfo 包含更新信息的ReaderInfo对象
     * @return 编辑成功返回true，否则返回false
     */
    public boolean editReaderCard(ReaderInfo readerInfo) {
        return readerInfoDao.editReaderCard(readerInfo) > 0;
    }


    /**
     * 添加读者信息并创建借阅卡（带事务管理）
     * @param readerInfo 读者信息对象
     * @param password 读者密码
     * @return 添加成功返回读者ID，失败返回0
     */
    @Transactional
    public long addReaderInfoWithCard(ReaderInfo readerInfo, String password) {
        long readerId = readerInfoDao.addReaderInfo(readerInfo);
        if (readerId > 0) {
            readerInfo.setReaderId(readerId);
            boolean cardAdded = readerCardService.addReaderCard(readerInfo, password);
            if (cardAdded) {
                return readerId;
            } else {
                // 手动回滚事务
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return -1L;
            }
        }
        return -1L;
    }

    /**
     * 分页和模糊查询读者信息
     * @param page 页码（从1开始）
     * @param pageSize 每页条数
     * @param name 姓名模糊查询
     * @return 读者信息列表
     */
    public List<ReaderInfo> getReaderInfoByPageAndName(int page, int pageSize, String name) {
        int offset = (page - 1) * pageSize;
        return readerInfoDao.getReaderInfoByPageAndName(offset, pageSize, name);
    }

    /**
     * 获取模糊查询后的总数
     * @param name 姓名模糊查询
     * @return 总数
     */
    public int getReaderInfoCountByName(String name) {
        return readerInfoDao.getReaderInfoCountByName(name);
    }
}
