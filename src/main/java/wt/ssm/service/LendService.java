package wt.ssm.service;

import wt.ssm.bean.Lend;
import wt.ssm.dao.LendDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 借阅服务类，处理图书借阅、归还、查询借阅记录等业务逻辑
 */
@Service
public class LendService {
    @Autowired
    private LendDao lendDao;

    /**
     * 归还图书
     * @param bookId 图书ID
     * @param readerId 读者ID
     * @return 归还成功返回true，否则返回false
     */
    public boolean returnBook(long bookId, long readerId){
        return lendDao.returnBookOne(bookId,readerId)>0 && lendDao.returnBookTwo(bookId)>0;
    }

    /**
     * 借阅图书
     * @param bookId 图书ID
     * @param readerId 读者ID
     * @return 借阅成功返回true，否则返回false
     */
    public boolean lendBook(long bookId,long readerId){
        return lendDao.lendBookOne(bookId,readerId)>0 && lendDao.lendBookTwo(bookId)>0;
    }

    /**
     * 获取所有借阅记录
     * @return 包含所有借阅记录的ArrayList集合
     */
    public ArrayList<Lend> lendList(){
        return lendDao.lendList();
    }

    /**
     * 获取管理员视图的借阅记录总数
     * @return 借阅记录总数
     */
    public int getAdminLendCount(){
        return lendDao.getAdminLendCount();
    }

    /**
     * 分页获取管理员视图的借阅记录列表
     * @param currentPage 当前页码（从1开始）
     * @param pageSize 每页记录数
     * @return 分页后的借阅记录列表
     */
    public List<Lend> getAdminLendListByPage(int currentPage, int pageSize){
        int start = (currentPage - 1) * pageSize;
        return lendDao.getAdminLendListByPage(start, pageSize);
    }

    /**
     * 获取指定读者的借阅记录
     * @param readerId 读者ID
     * @return 包含该读者所有借阅记录的ArrayList集合
     */
    public ArrayList<Lend> myLendList(long readerId){
        return lendDao.myLendList(readerId);
    }

    /**
     * 获取读者借阅记录总数
     * @param readerId 读者ID
     * @return 借阅记录总数
     */
    public int getMyLendCount(long readerId){
        return lendDao.getMyLendCount(readerId);
    }

    /**
     * 分页获取读者借阅记录
     * @param readerId 读者ID
     * @param currentPage 当前页码
     * @param pageSize 每页记录数
     * @return 分页借阅记录列表
     */
    public List<Lend> getMyLendListByPage(long readerId, int currentPage, int pageSize){
        int offset = (currentPage - 1) * pageSize;
        return lendDao.getMyLendListByPage(readerId, offset, pageSize);
    }

    /**
     * 删除借阅记录
     * @param serNum 借阅记录编号
     * @return 受影响的行数，大于0表示删除成功
     */
    public int deleteLend(long serNum) {
        return lendDao.deleteLend(serNum);
    }

}
