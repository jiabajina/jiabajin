package wt.ssm.controller;

import wt.ssm.bean.Lend;
import wt.ssm.bean.ReaderCard;
import wt.ssm.service.BookService;
import wt.ssm.service.LendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 借阅控制器，处理图书借阅、归还、删除借阅记录等相关请求
 */
@Controller
public class LendController {
    @Autowired
    private LendService lendService;

    @Autowired
    private BookService bookService;

    /**
     * 删除图书
     * @param request 请求对象，包含要删除的图书ID
     * @param redirectAttributes 重定向属性，用于传递提示信息
     * @return 重定向到管理员图书列表页面
     */
    @RequestMapping("/deletebook")
    public String deleteBook(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        if (bookService.deleteBook(bookId)) {
            redirectAttributes.addFlashAttribute("succ", "图书删除成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "图书删除失败！");
        }
        return "redirect:/admin_books";
    }

    /**
     * 获取所有借阅记录列表
     * @param currentPage 当前页码，默认为1
     * @return 包含借阅记录列表的ModelAndView，跳转到管理员借阅记录页面
     */
    @RequestMapping("/lendlist")
    public ModelAndView lendList(@RequestParam(defaultValue = "1") int currentPage) {
        int pageSize = 10;
        int total = lendService.getAdminLendCount();
        int totalPages = (int) Math.ceil((double) total / pageSize);
        if (currentPage < 1) currentPage = 1;
        if (currentPage > totalPages && totalPages > 0) currentPage = totalPages;
        
        List<Lend> list = lendService.getAdminLendListByPage(currentPage, pageSize);
        
        ModelAndView modelAndView = new ModelAndView("admin_lend_list");
        modelAndView.addObject("list", list);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.addObject("totalPages", totalPages);
        return modelAndView;
    }

    /**
     * 获取当前读者的借阅记录
     * @param request 请求对象，从中获取当前登录读者信息
     * @return 包含个人借阅记录的ModelAndView，跳转到读者借阅记录页面
     */
    @RequestMapping("/mylend")
    public ModelAndView myLend(HttpServletRequest request, @RequestParam(defaultValue = "1") int currentPage) {
        int pageSize = 10;
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        long readerId = readerCard.getReaderId();
        
        int total = lendService.getMyLendCount(readerId);
        int totalPages = (int) Math.ceil((double) total / pageSize);
        if (currentPage < 1) currentPage = 1;
        if (currentPage > totalPages && totalPages > 0) currentPage = totalPages;
        
        List<Lend> list = lendService.getMyLendListByPage(readerId, currentPage, pageSize);
        
        ModelAndView modelAndView = new ModelAndView("reader_lend_list");
        modelAndView.addObject("list", list);
        modelAndView.addObject("currentPage", currentPage);
        modelAndView.addObject("totalPages", totalPages);
        return modelAndView;
    }

    /**
     * 删除借阅记录
     * @param request 请求对象，包含要删除的借阅记录编号
     * @param redirectAttributes 重定向属性，用于传递提示信息
     * @return 重定向到借阅记录列表页面
     */
    @RequestMapping("/deletelend")
    public String deleteLend(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long serNum = Long.parseLong(request.getParameter("serNum"));
        if (lendService.deleteLend(serNum) > 0) {
            redirectAttributes.addFlashAttribute("succ", "记录删除成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "记录删除失败！");
        }
        return "redirect:/lendlist";
    }

    /**
     * 借阅图书
     * @param request 请求对象，包含要借阅的图书ID和当前读者信息
     * @param redirectAttributes 重定向属性，用于传递提示信息
     * @return 重定向到读者图书列表页面
     */
    @RequestMapping("/lendbook")
    public String bookLend(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        long readerId = ((ReaderCard) request.getSession().getAttribute("readercard")).getReaderId();
        if (lendService.lendBook(bookId, readerId)) {
            redirectAttributes.addFlashAttribute("succ", "图书借阅成功！");
        } else {
            redirectAttributes.addFlashAttribute("succ", "图书借阅成功！");
        }
        return "redirect:/reader_books";
    }

    /**
     * 归还图书
     * @param request 请求对象，包含要归还的图书ID和当前读者信息
     * @param redirectAttributes 重定向属性，用于传递提示信息
     * @return 重定向到读者图书列表页面
     */
    @RequestMapping("/returnbook")
    public String bookReturn(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        long readerId = ((ReaderCard) request.getSession().getAttribute("readercard")).getReaderId();
        if (lendService.returnBook(bookId, readerId)) {
            redirectAttributes.addFlashAttribute("succ", "图书归还成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "图书归还失败！");
        }
        return "redirect:/reader_books";
    }
}
