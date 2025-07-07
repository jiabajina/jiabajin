package wt.ssm.controller;

import wt.ssm.bean.ReaderCard;
import wt.ssm.bean.ReaderInfo;
import wt.ssm.service.LoginService;
import wt.ssm.service.ReaderCardService;
import wt.ssm.service.ReaderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 读者控制器，处理读者信息管理相关请求，包括读者列表查询、添加、编辑、删除等操作
 */
@Controller
public class ReaderController {
    @Autowired
    private ReaderInfoService readerInfoService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ReaderCardService readerCardService;

    /**
     * 构建ReaderInfo对象
     * @param readerId 读者ID（新增时为0）
     * @param name 读者姓名
     * @param sex 读者性别
     * @param birth 出生日期字符串（格式：yyyy-MM-dd）
     * @param address 读者地址
     * @param phone 读者电话
     * @return 构建好的ReaderInfo对象
     */
    private ReaderInfo getReaderInfo(long readerId, String name, String sex, String birth, String address, String phone) {
        ReaderInfo readerInfo = new ReaderInfo();
        Date date = new Date();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            date = df.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        readerInfo.setAddress(address);
        readerInfo.setName(name);
        readerInfo.setReaderId(readerId);
        readerInfo.setPhone(phone);
        readerInfo.setSex(sex);
        readerInfo.setBirth(date);
        return readerInfo;
    }

    /**
     * 跳转到系统首页
     * @return 包含首页视图的ModelAndView
     */
    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    /**
     * 获取所有读者信息列表（管理员视图）
     * @return 包含读者列表的ModelAndView，跳转到admin_readers页面
     */
    @RequestMapping("/allreaders")
    public ModelAndView allBooks(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "name", required = false) String name) {
        int pageSize = 10;
        if (name == null) name = "";
        int total = readerInfoService.getReaderInfoCountByName(name);
        int totalPages = (int) Math.ceil((double) total / pageSize);
        if (page < 1) page = 1;
        if (page > totalPages && totalPages > 0) page = totalPages;
        List<ReaderInfo> readers = readerInfoService.getReaderInfoByPageAndName(page, pageSize, name);
        ModelAndView modelAndView = new ModelAndView("admin_readers");
        modelAndView.addObject("readers", readers);
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("totalPages", totalPages);
        modelAndView.addObject("name", name);
        return modelAndView;
    }

    /**
     * 获取所有读者信息列表（管理员备用视图）
     * @return 包含读者列表的ModelAndView，跳转到admin_readers1页面
     */
    @RequestMapping("/allreaders1")
    public ModelAndView allBooks1(@RequestParam(required = false) Long readerId, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin_readers1");
        // 添加flash属性到模型
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            modelAndView.addAllObjects(flashMap);
        }
        if (readerId != null) {
            ReaderInfo reader = readerInfoService.getReaderInfo(readerId);
            if (reader != null) {
                modelAndView.addObject("reader", reader);
            } else {
                modelAndView.addObject("error", "未找到该读者信息");
            }
        }
        return modelAndView;
    }

    /**
     * 删除读者信息
     * @param request 请求对象，包含要删除的读者ID
     * @param redirectAttributes 重定向属性，用于传递提示信息
     * @return 重定向到读者列表页面
     */
    @RequestMapping("/reader_delete")
    public String readerDelete(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long readerId = Long.parseLong(request.getParameter("readerId"));
        if (readerInfoService.deleteReaderInfo(readerId) && readerCardService.deleteReaderCard(readerId)) {
            redirectAttributes.addFlashAttribute("succ", "删除成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "删除失败！");
        }
        return "redirect:/allreaders";
    }

    /**
     * 查看当前读者个人信息
     * @param request 请求对象，从中获取当前登录读者的ReaderCard
     * @return 包含读者信息的ModelAndView，跳转到reader_info页面；未登录则重定向到登录页
     */
    @RequestMapping("/reader_info")
    public ModelAndView toReaderInfo(HttpServletRequest request) {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        if (readerCard == null) {
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            return modelAndView;
        }
        ReaderInfo readerInfo = readerInfoService.getReaderInfo(readerCard.getReaderId());
        ModelAndView modelAndView = new ModelAndView("reader_info");
        if (readerInfo == null) {
            modelAndView.addObject("error", "未找到读者信息");
        } else {
            modelAndView.addObject("readerinfo", readerInfo);
        }
        return modelAndView;
    }

    /**
     * 跳转到管理员编辑读者信息页面
     * @param request 请求对象，包含要编辑的读者ID
     * @return 包含读者信息的ModelAndView，跳转到admin_reader_edit页面
     */
    @RequestMapping("/reader_edit")
    public ModelAndView readerInfoEdit(HttpServletRequest request) {
        long readerId = Long.parseLong(request.getParameter("readerId"));
        ReaderInfo readerInfo = readerInfoService.getReaderInfo(readerId);
        ModelAndView modelAndView = new ModelAndView("admin_reader_edit");
        modelAndView.addObject("readerInfo", readerInfo);
        return modelAndView;
    }

    /**
     * 处理管理员编辑读者信息的提交
     * @param request 请求对象，包含读者ID
     * @param name 读者姓名
     * @param sex 读者性别
     * @param birth 出生日期
     * @param address 读者地址
     * @param phone 读者电话
     * @param redirectAttributes 重定向属性，用于传递提示信息
     * @return 重定向到读者列表页面
     */
    @RequestMapping("/reader_edit_do")
    public String readerInfoEditDo(HttpServletRequest request, String name, String sex, String birth, String address, String phone, RedirectAttributes redirectAttributes) {
        long readerId = Long.parseLong(request.getParameter("readerId"));
        ReaderInfo readerInfo = getReaderInfo(readerId, name, sex, birth, address, phone);
        if (readerInfoService.editReaderInfo(readerInfo) && readerInfoService.editReaderCard(readerInfo)) {
            redirectAttributes.addFlashAttribute("succ", "读者信息修改成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "读者信息修改失败！");
        }
        return "redirect:/allreaders";
    }

    /**
     * 跳转到管理员添加读者页面
     * @return 跳转到admin_reader_add页面的ModelAndView
     */
    @RequestMapping("/reader_add")
    public ModelAndView readerInfoAdd() {
        return new ModelAndView("admin_reader_add");
    }

    /**
     * 跳转到读者注册页面
     * @return 跳转到registered_reader_add页面的ModelAndView
     */
    @RequestMapping("/reader1_add")
    public ModelAndView readerInfoAdd1() {
        return new ModelAndView("registered_reader_add");
    }

    /**
     * 处理管理员添加读者信息的提交
     * @param name 读者姓名
     * @param sex 读者性别
     * @param birth 出生日期
     * @param address 读者地址
     * @param phone 读者电话
     * @param password 读者密码
     * @param redirectAttributes 重定向属性，用于传递提示信息
     * @return 重定向到读者列表页面
     */
    @RequestMapping("/reader_add_do")
    public String readerInfoAddDo(String name, String sex, String birth, String address, String phone, String password, RedirectAttributes redirectAttributes) {
        try {
            ReaderInfo readerInfo = getReaderInfo(0, name, sex, birth, address, phone);
            long readerId = readerInfoService.addReaderInfoWithCard(readerInfo, password);
            if (readerId > 0) {
                redirectAttributes.addFlashAttribute("succ", "添加读者成功！");
            } else {
                redirectAttributes.addFlashAttribute("error", "添加读者失败！");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "添加读者失败：" + e.getMessage());
        }
        return "redirect:/allreaders";
    }

    /**
     * 读者信息添加辅助方法，处理读者信息和读者卡的创建
     * @param name 读者姓名
     * @param sex 读者性别
     * @param birth 出生日期
     * @param address 读者地址
     * @param phone 读者电话
     * @param password 读者密码
     * @param redirectAttributes 重定向属性，用于传递提示信息
     */
    @RequestMapping("/reader1_add_do")
    public String readerSelfRegisterDo(String name, String sex, String birth, String address, String phone, String password, RedirectAttributes redirectAttributes) {
        try {
            ReaderInfo readerInfo = getReaderInfo(0, name, sex, birth, address, phone);
            long readerId = readerInfoService.addReaderInfoWithCard(readerInfo, password);
            if (readerId > 0) {
                redirectAttributes.addFlashAttribute("succ", "注册成功！您的读者ID是：" + readerId);
                return "redirect:/login";
            } else {
                redirectAttributes.addFlashAttribute("error", "注册失败，请重试！");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "注册失败：" + e.getMessage());
        }
        return "redirect:/reader1_add";
    }

    /**
     * 处理读者注册信息的提交
     * @param name 读者姓名
     * @param sex 读者性别
     * @param birth 出生日期
     * @param address 读者地址
     * @param phone 读者电话
     * @param password 读者密码
     * @param redirectAttributes 重定向属性，用于传递提示信息
     * @return 重定向到读者列表备用页面
     */
    @RequestMapping("/reader_add_do1")
    public String readerInfoAddDo1(String name, String sex, String birth, String address, String phone, String password, RedirectAttributes redirectAttributes) {
        try {
            ReaderInfo readerInfo = getReaderInfo(0, name, sex, birth, address, phone);
            long readerId = readerInfoService.addReaderInfoWithCard(readerInfo, password);
            if (readerId > 0) {
                redirectAttributes.addFlashAttribute("succ", "添加读者成功！");
                return "redirect:/allreaders1?readerId=" + readerId;
            } else {
                redirectAttributes.addFlashAttribute("error", "添加读者失败！");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "添加读者失败：" + e.getMessage());
        }
        return "redirect:/allreaders1";
    }

    /**
     * 跳转到读者个人信息编辑页面
     * @param request 请求对象，从中获取当前登录读者信息
     * @return 包含读者信息的ModelAndView，跳转到reader_info_edit页面
     */
    @RequestMapping("/reader_info_edit")
    public ModelAndView readerInfoEditReader(HttpServletRequest request) {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        if (readerCard == null) {
            return new ModelAndView("redirect:/login");
        }
        ReaderInfo readerInfo = readerInfoService.getReaderInfo(readerCard.getReaderId());
        ModelAndView modelAndView = new ModelAndView("reader_info_edit");
        modelAndView.addObject("readerinfo", readerInfo);
        return modelAndView;
    }

    /**
     * 处理读者个人信息编辑的提交
     * @param request 请求对象，包含当前登录读者的Session信息
     * @param name 读者姓名
     * @param sex 读者性别
     * @param birth 出生日期
     * @param address 读者地址
     * @param phone 读者电话
     * @param redirectAttributes 重定向属性，用于传递提示信息
     * @return 重定向到读者个人信息页面
     */
    @RequestMapping("/reader_edit_do_r")
    public String readerInfoEditDoReader(HttpServletRequest request, String name, String sex, String birth, String address, String phone, RedirectAttributes redirectAttributes) {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readercard");
        ReaderInfo readerInfo = getReaderInfo(readerCard.getReaderId(), name, sex, birth, address, phone);
        if (readerInfoService.editReaderInfo(readerInfo) && readerInfoService.editReaderCard(readerInfo)) {
            ReaderCard readerCardNew = loginService.findReaderCardByReaderId(readerCard.getReaderId());
            request.getSession().setAttribute("readercard", readerCardNew);
            redirectAttributes.addFlashAttribute("succ", "信息修改成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "信息修改失败！");
        }
        return "redirect:/reader_info";
    }
}
