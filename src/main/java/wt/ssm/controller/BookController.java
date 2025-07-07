package wt.ssm.controller;

import wt.ssm.bean.Book;
import wt.ssm.bean.ClassInfo;
import wt.ssm.bean.Lend;
import wt.ssm.bean.ReaderCard;
import wt.ssm.service.BookService;
import wt.ssm.service.ClassInfoService;
import wt.ssm.service.LendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 图书控制器，处理图书相关的HTTP请求，包括管理员和读者的图书查询、添加、编辑、删除及详情查看等操作
 */
@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private LendService lendService;
    @Autowired
    private ClassInfoService classInfoService;

    /**
     * 将字符串格式的日期转换为Date对象
     * @param pubstr 日期字符串，格式为yyyy-MM-dd
     * @return 转换后的Date对象，若转换失败则返回当前日期
     */
    private Date getDate(String pubstr) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.parse(pubstr);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    /**
     * 管理员图书搜索功能
     * @param searchWord 搜索关键词
     * @return 包含搜索结果的ModelAndView对象，若有匹配图书则跳转至admin_books页面并携带图书列表，否则携带错误信息
     */
    @RequestMapping("/querybook")
    public ModelAndView queryBookDo(String searchWord, @RequestParam(defaultValue = "1") int currentPage) {
        int pageSize = 10;
        if (bookService.matchBook(searchWord)) {
            ArrayList<Book> books = bookService.queryBookByPage(searchWord, currentPage, pageSize);
            int totalSearchBooks = bookService.getQueryBookTotal(searchWord);
            int totalPages = (int) Math.ceil((double) totalSearchBooks / pageSize);
            ModelAndView modelAndView = new ModelAndView("admin_books");
            modelAndView.addObject("books", books);
            modelAndView.addObject("currentPage", currentPage);
            modelAndView.addObject("totalPages", totalPages);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("admin_books");
            modelAndView.addObject("error", "没有匹配的图书");
            modelAndView.addObject("currentPage", currentPage);
            modelAndView.addObject("totalPages", 0);
            return modelAndView;
        }
    }

    /**
     * 读者图书搜索功能
     * @param searchWord 搜索关键词
     * @return 包含搜索结果的ModelAndView对象，若有匹配图书则跳转至reader_books页面并携带图书列表，否则携带错误信息
     */
    @RequestMapping("/reader_querybook_do")
    public ModelAndView readerQueryBookDo(String searchWord, @RequestParam(defaultValue = "1") int currentPage) {
        int pageSize = 10;
        if (bookService.matchBook(searchWord)) {
            ArrayList<Book> books = bookService.queryBookByPage(searchWord, currentPage, pageSize);
            int totalSearchBooks = bookService.getQueryBookTotal(searchWord);
            int totalPages = (int) Math.ceil((double) totalSearchBooks / pageSize);
            ModelAndView modelAndView = new ModelAndView("reader_books");
            modelAndView.addObject("books", books);
            modelAndView.addObject("currentPage", currentPage);
            modelAndView.addObject("totalPages", totalPages);
            modelAndView.addObject("searchWord", searchWord);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("reader_books");
            modelAndView.addObject("error", "没有匹配的图书");
            modelAndView.addObject("currentPage", currentPage);
            modelAndView.addObject("totalPages", 0);
            modelAndView.addObject("searchWord", searchWord);
            return modelAndView;
        }
    }

    /**
     * 管理员查看所有图书
     * @return 包含所有图书列表的ModelAndView对象，跳转至admin_books页面
     */
    @RequestMapping("/admin_books")
    public ModelAndView adminBooks(@RequestParam(defaultValue = "1") int currentPage) {
        int pageSize = 10;
        ArrayList<Book> books = bookService.getBooksByPage(currentPage, pageSize);
        int totalBooks = bookService.getTotalBooks();
        int totalPages = (int) Math.ceil((double) totalBooks / pageSize);

        ModelAndView modelAndView = new ModelAndView("admin_books");
        modelAndView.addObject("books", books);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.addObject("totalPages", totalPages);
        return modelAndView;
    }

    /**
     * 跳转到添加图书页面
     * @return 指向admin_book_add页面的ModelAndView对象
     */
    @RequestMapping("/book_add")
    public ModelAndView addBook() {
        ModelAndView modelAndView = new ModelAndView("admin_book_add");
        // 查询所有分类并传递到前端
        java.util.List<ClassInfo> classList = classInfoService.getAllClasses();
        modelAndView.addObject("classList", classList);
        return modelAndView;
    }

    /**
     * 执行添加图书操作
     * @param pubstr 出版日期字符串（格式：yyyy-MM-dd）
     * @param book 图书对象��包含图书信息
     * @param redirectAttributes 重定向属性，用于传递操作结果消息
     * @return 重定向至/admin_books页面
     */
    @RequestMapping("/book_add_do")
    public String addBookDo(@RequestParam(value = "pubstr") String pubstr, Book book, RedirectAttributes redirectAttributes) {
        book.setPubdate(getDate(pubstr));
        if (bookService.addBook(book)) {
            redirectAttributes.addFlashAttribute("succ", "图书添加成功！");
        } else {
            redirectAttributes.addFlashAttribute("succ", "图书添加失败！");
        }
        return "redirect:/admin_books";
    }

    /**
     * 跳转到图书编辑页面
     * @param request HTTP请求对象，包含图书ID参数
     * @return 包含图书详情的ModelAndView对象，跳转至admin_book_edit页面
     */
    @RequestMapping("/updatebook")
    public ModelAndView bookEdit(HttpServletRequest request) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        Book book = bookService.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("admin_book_edit");
        modelAndView.addObject("detail", book);
        // 查询所有分类并传递到前端
        java.util.List<ClassInfo> classList = classInfoService.getAllClasses();
        modelAndView.addObject("classList", classList);
        return modelAndView;
    }

    /**
     * 执行图书编辑操作
     * @param pubstr 出版日期字符串（格式：yyyy-MM-dd）
     * @param book 图书对象，包含更新后的图书信息
     * @param redirectAttributes 重定向属性，用于传递操作结果消息
     * @return 重定向至/admin_books页面
     */
    @RequestMapping("/book_edit_do")
    public String bookEditDo(@RequestParam(value = "pubstr") String pubstr, Book book, RedirectAttributes redirectAttributes) {
        book.setPubdate(getDate(pubstr));
        if (bookService.editBook(book)) {
            redirectAttributes.addFlashAttribute("succ", "图书修改成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "图书修改失败！");
        }
        return "redirect:/admin_books";
    }

    /**
     * 管理员查看图书详情
     * @param request HTTP请求对象，包含图书ID参数
     * @return 包含图书详情的ModelAndView对象，跳转至admin_book_detail页面
     */
    @RequestMapping("/admin_book_detail")
    public ModelAndView adminBookDetail(HttpServletRequest request) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        Book book = bookService.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("admin_book_detail");
        modelAndView.addObject("detail", book);
        // 新增：查找分类名称
        ClassInfo classInfo = classInfoService.getClassInfoById((long)book.getClassId());
        String className = classInfo != null ? classInfo.getClassName() : "";
        modelAndView.addObject("className", className);
        return modelAndView;
    }

    /**
     * 读者查看图书详情
     * @param request HTTP请求对象，包含图书ID参数
     * @return 包含图书详情的ModelAndView对象，跳转至reader_book_detail页面
     */
    @RequestMapping("/reader_book_detail")
    public ModelAndView readerBookDetail(HttpServletRequest request) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        Book book = bookService.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("reader_book_detail");
        modelAndView.addObject("detail", book);
        // 新增：查找分类名称
        ClassInfo classInfo = classInfoService.getClassInfoById((long)book.getClassId());
        String className = classInfo != null ? classInfo.getClassName() : "";
        modelAndView.addObject("className", className);
        return modelAndView;
    }

    /**
     * 加载管理员头部导航栏
     * @return 指向admin_header页面的ModelAndView对象
     */
    @RequestMapping("/admin_header")
    public ModelAndView admin_header() {
        return new ModelAndView("admin_header");
    }

    /**
     * 加载读者头部导航栏
     * @return 指向reader_header页面的ModelAndView对象
     */
    @RequestMapping("/reader_header")
    public ModelAndView reader_header() {
        return new ModelAndView("reader_header");
    }

    /**
     * 读者查看所有图书及个人借阅状态
     * @param request HTTP请求对象，用于获取当前读者会话信息
     * @return 包含图书列表和个人未归还图书ID列表的ModelAndView对象，跳转至reader_books页面
     */
    @RequestMapping("/reader_books")
    public ModelAndView readerBooks(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage) {
        int pageSize = 10;
        ArrayList<Book> books = bookService.getBooksByPage(currentPage, pageSize);
        int totalBooks = bookService.getTotalBooks();
        int totalPages = (int) Math.ceil((double) totalBooks / pageSize);

        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        // 检查读者会话是否存在
        if (readerCard == null) {
            return new ModelAndView("redirect:/login");
        }
        ArrayList<Lend> myAllLendList = lendService.myLendList(readerCard.getReaderId());
        ArrayList<Long> myLendList = new ArrayList<>();
        for (Lend lend : myAllLendList) {
            // 是否已归还
            if (lend.getBackDate() == null) {
                myLendList.add(lend.getBookId());
            }
        }
        ModelAndView modelAndView = new ModelAndView("reader_books");
        modelAndView.addObject("books", books);
        modelAndView.addObject("myLendList", myLendList);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.addObject("totalPages", totalPages);
        return modelAndView;
    }
}
