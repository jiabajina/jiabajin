package wt.ssm.dao;

import wt.ssm.bean.Book;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 图书数据访问对象，负责图书相关的数据库操作
 */
@Repository
public class BookDao {

    private final static String NAMESPACE = "wt.ssm.dao.BookDao.";
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 搜索匹配的图书数量
     * @param searchWord 搜索关键词
     * @return 匹配的图书数量
     */
    public int matchBook(final String searchWord) {
        String search = "%" + searchWord + "%";
        return sqlSessionTemplate.selectOne(NAMESPACE + "matchBook", search);
    }

    /**
     * 根据关键词搜索图书
     * @param searchWord 搜索关键词
     * @return 匹配的图书列表
     */
    public ArrayList<Book> queryBook(final String searchWord) {
        String search = "%" + searchWord + "%";
        List<Book> result = sqlSessionTemplate.selectList(NAMESPACE + "queryBook", search);
        return (ArrayList<Book>) result;
    }

    /**
     * 分页搜索图书
     * @param params 包含搜索关键词(search)、起始位置(offset)和每页条数(pageSize)的参数Map
     * @return 分页后的图书列表
     */
    public ArrayList<Book> queryBookByPage(Map<String, Object> params) {
        List<Book> result = sqlSessionTemplate.selectList(NAMESPACE + "queryBookByPage", params);
        return (ArrayList<Book>) result;
    }

    /**
     * 获取搜索结果的总记录数
     * @param searchWord 搜索关键词
     * @return 符合条件的图书总数
     */
    public int getQueryBookTotal(String searchWord) {
        String search = "%" + searchWord + "%";
        return sqlSessionTemplate.selectOne(NAMESPACE + "getQueryBookTotal", search);
    }

    /**
     * 获取所有图书
     * @return 图书列表
     */
    public ArrayList<Book> getAllBooks() {
        List<Book> result = sqlSessionTemplate.selectList(NAMESPACE + "getAllBooks");
        return (ArrayList<Book>) result;
    }

    /**
     * 添加图书
     * @param book 图书对象
     * @return 受影响的行数，大于0表示添加成功
     */
    public int addBook(final Book book) {
        return sqlSessionTemplate.insert(NAMESPACE + "addBook", book);
    }

    /**
     * 根据图书ID获取图书信息
     * @param bookId 图书ID
     * @return 对应的Book对象，若不存在则返回null
     */
    public Book getBook(final long bookId) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getBook", bookId);
    }

    /**
     * 编辑图书信息
     * @param book 图书对象
     * @return 受影响的行数，大于0表示更新成功
     */
    public int editBook(final Book book) {
        return sqlSessionTemplate.update(NAMESPACE + "editBook", book);
    }

    /**
     * 删除图书
     * @param bookId 图书ID
     * @return 受影响的行数，大于0表示删除成功
     */
    public int deleteBook(final long bookId) {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteBook", bookId);
    }

    /**
     * 分页获取所有图书
     * @param params 包含起始位置(offset)和每页条数(pageSize)的参数Map
     * @return 分页后的图书列表
     */
    public ArrayList<Book> getBooksByPage(Map<String, Integer> params) {
        List<Book> result = sqlSessionTemplate.selectList(NAMESPACE + "getBooksByPage", params);
        return (ArrayList<Book>) result;
    }

    /**
     * 获取图书总记录数
     * @return 系统中的图书总数
     */
    public int getTotalBooks() {
        return sqlSessionTemplate.selectOne(NAMESPACE + "getTotalBooks");
    }
}