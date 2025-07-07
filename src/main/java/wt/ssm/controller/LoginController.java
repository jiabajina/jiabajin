package wt.ssm.controller;

import wt.ssm.bean.Admin;
import wt.ssm.bean.ReaderCard;
import wt.ssm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.*;
import java.util.HashMap;

/**
 * 登录控制器，处理用户登录、注销、密码修改等认证相关请求
 */
@Controller
public class LoginController {

    private LoginService loginService;


    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 跳转到登录页面，并使当前会话失效
     * @param request 请求对象
     * @return 登录页面视图名
     */
    @RequestMapping(value = {"/", "/login"})
    public String toLogin(HttpServletRequest request) {
        request.getSession().invalidate();
        return "index";
    }

    /**
     * 用户注销，使当前会话失效并跳转到登录页面
     * @param request 请求对象
     * @return 重定向到登录页面
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }

    /**
     * 登录验证API接口
     * @param request 请求对象，包含用户ID和密码参数
     * @return JSON格式的响应，包含状态码和消息：
     *         - stateCode: 1(管理员登录成功), 2(读者登录成功), 0(账号密码错误), -1(参数缺失), -3(账号格式错误), 500(服务器异常)
     *         - msg: 对应的提示消息
     */
    @RequestMapping(value = "/api/loginCheck", method = RequestMethod.POST)
    public @ResponseBody
    Object loginCheck(HttpServletRequest request) {
        HashMap<String, String> res = new HashMap<>();
        try {
            HttpSession session = request.getSession();
            String idStr = request.getParameter("id");
            String passwd = request.getParameter("passwd");

            if (idStr == null || passwd == null) {
                res.put("stateCode", "-1");
                res.put("msg", "参数缺失");
                return res;
            }
            long id;
            try {
                id = Long.parseLong(idStr);
            } catch (NumberFormatException e) {
                res.put("stateCode", "-3");
                res.put("msg", "账号格式错误");
                return res;
            }
            boolean isReader = loginService.hasMatchReader(id, passwd);
            boolean isAdmin = loginService.hasMatchAdmin(id, passwd);
            if (isAdmin) {
                Admin admin = new Admin();
                admin.setAdminId(id);
                admin.setPassword(passwd);
                String username = loginService.getAdminUsername(id);
                admin.setUsername(username);
                request.getSession().setAttribute("admin", admin);
                res.put("stateCode", "1");
                res.put("msg", "管理员登陆成功！");
            } else if (isReader) {
                ReaderCard readerCard = loginService.findReaderCardByReaderId(id);
                request.getSession().setAttribute("readercard", readerCard);
                res.put("stateCode", "2");
                res.put("msg", "读者登陆成功！");
            } else {
                res.put("stateCode", "0");
                res.put("msg", "账号或密码错误！");
            }
        } catch (Exception e) {
            res.put("stateCode", "500");
            res.put("msg", "服务器异常: " + e.getMessage());
        }
        return res;
    }

    /**
     * 跳转到管理员主页面
     * @param response 响应对象
     * @return 包含管理员主页面视图的ModelAndView
     */
    @RequestMapping("/admin_main")
    public ModelAndView toAdminMain(HttpServletResponse response) {
        return new ModelAndView("admin_main");
    }

    /**
     * 跳转到读者主页面
     * @param response 响应对象
     * @return 包含读者主页面视图的ModelAndView
     */
    @RequestMapping("/reader_main")
    public ModelAndView toReaderMain(HttpServletResponse response) {
        return new ModelAndView("reader_main");
    }

    /**
     * 跳转到管理员密码修改页面
     * @return 包含管理员密码修改页面视图的ModelAndView
     */
    @RequestMapping("/admin_repasswd")
    public ModelAndView reAdminPasswd() {
        return new ModelAndView("admin_repasswd");
    }

    /**
     * 处理管理员密码修改请求
     * @param request 请求对象，包含当前登录管理员信息
     * @param oldPasswd 旧密码
     * @param newPasswd 新密码
     * @param reNewPasswd 确认新密码
     * @param redirectAttributes 重定向属性，用于传递提示信息
     * @return 重定向到管理员密码修改页面
     */
    @RequestMapping("/admin_repasswd_do")
    public String reAdminPasswdDo(HttpServletRequest request, String oldPasswd, String newPasswd, String reNewPasswd, RedirectAttributes redirectAttributes) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        long id = admin.getAdminId();
        String password = loginService.getAdminPassword(id);
        if (password.equals(oldPasswd)) {
            if (loginService.adminRePassword(id, newPasswd)) {
                redirectAttributes.addFlashAttribute("succ", "密码修改成功！");
                return "redirect:/admin_repasswd";
            } else {
                redirectAttributes.addFlashAttribute("error", "密码修改失败！");
                return "redirect:/admin_repasswd";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "旧密码错误！");
            return "redirect:/admin_repasswd";
        }
    }

    /**
     * 跳转到读者密码修改页面
     * @return 包含读者密码修改页面视图的ModelAndView
     */
    @RequestMapping("/reader_repasswd")
    public ModelAndView reReaderPasswd() {
        return new ModelAndView("reader_repasswd");
    }

    /**
     * 处理读者密码修改请求
     * @param request 请求对象，包含当前登录读者信息
     * @param oldPasswd 旧密码
     * @param newPasswd 新密码
     * @param reNewPasswd 确认新密码
     * @param redirectAttributes 重定向属性，用于传递提示信息
     * @return 重定向到读者密码修改页面
     */
    @RequestMapping("/reader_repasswd_do")
    public String reReaderPasswdDo(HttpServletRequest request, String oldPasswd, String newPasswd, String reNewPasswd, RedirectAttributes redirectAttributes) {
        ReaderCard reader = (ReaderCard) request.getSession().getAttribute("readercard");
        if (reader == null) {
            return "redirect:/login";
        }
        long id = reader.getReaderId();
        String password = loginService.getReaderPassword(id);
        if (password.equals(oldPasswd)) {
            if (loginService.readerRePassword(id, newPasswd)) {
                redirectAttributes.addFlashAttribute("succ", "密码修改成功！");
                return "redirect:/reader_repasswd";
            } else {
                redirectAttributes.addFlashAttribute("error", "密码修改失败！");
                return "redirect:/reader_repasswd";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "旧密码错误！");
            return "redirect:/reader_repasswd";
        }
    }

    /**
     * 404页面处理，匹配所有未定义的请求路径
     * @return 404错误页面视图名
     */
    @RequestMapping("*")
    public String notFind() {
        return "404";
    }

}