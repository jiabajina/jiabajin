package wt.ssm.service;

import wt.ssm.bean.Book;
import wt.ssm.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * 图书服务类，处理图书的查询、添加、编辑和删除等业务逻辑
 */
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    /**
     * 根据关键词搜索图书
     * @param searchWord 搜索关键词
     * @return 匹配的图书列表
     */
    public ArrayList<Book> queryBook(String searchWord) {
        return bookDao.queryBook(searchWord);
    }

    /**
     * 分页搜索图书
     * @param searchWord 搜索关键词
     * @param currentPage 当前页码（从1开始）
     * @param pageSize 每页显示条数
     * @return 分页后的图书列表
     */
    public ArrayList<Book> queryBookByPage(String searchWord, int currentPage, int pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("search", "%" + searchWord + "%");
        params.put("offset", (currentPage - 1) * pageSize);
        params.put("pageSize", pageSize);
        return bookDao.queryBookByPage(params);
    }

    /**
     * 获取搜索结果的总记录数
     * @param searchWord 搜索关键词
     * @return 符合条件的图书总数
     */
    public int getQueryBookTotal(String searchWord) {
        return bookDao.getQueryBookTotal(searchWord);
    }

    /**
     * 获取所有图书
     * @return 图书列表
     */
    public ArrayList<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    /**
     * 检查是否存在匹配的图书
     * @param searchWord 搜索关键词
     * @return 存在匹配图书返回true，否则返回false
     */
    public boolean matchBook(String searchWord) {
        return bookDao.matchBook(searchWord) > 0;
    }

    /**
     * 添加图书
     * @param book 图书对象
     * @return 添加成功返回true，否则返回false
     */
    public boolean addBook(Book book) {
        return bookDao.addBook(book) > 0;
    }

    /**
     * 根据图书ID获取图书信息
     * @param bookId 图书ID
     * @return 对应的Book对象，若不存在则返回null
     */
    public Book getBook(Long bookId) {
        return bookDao.getBook(bookId);
    }

    /**
     * 编辑图书信息
     * @param book 图书对象
     * @return 编辑成功返回true，否则返回false
     */
    public boolean editBook(Book book) {
        return bookDao.editBook(book) > 0;
    }

    /**
     * 删除图书
     * @param bookId 图书ID
     * @return 删除成功返回true，否则返回false
     */
    public boolean deleteBook(Long bookId) {
        return bookDao.deleteBook(bookId) > 0;
    }

    /**
     * 分页获取所有图书
     * @param currentPage 当前页码（从1开始）
     * @param pageSize 每页显示条数
     * @return 分页后的图书列表
     */
    public ArrayList<Book> getBooksByPage(int currentPage, int pageSize) {
        Map<String, Integer> params = new HashMap<>();
        params.put("offset", (currentPage - 1) * pageSize);
        params.put("pageSize", pageSize);
        return bookDao.getBooksByPage(params);
    }

    /**
     * 获取图书总记录数
     * @return 系统中的图书总数
     */
    public int getTotalBooks() {
        return bookDao.getTotalBooks();
    }

}
