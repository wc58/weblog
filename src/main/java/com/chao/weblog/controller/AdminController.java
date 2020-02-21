package com.chao.weblog.controller;

import com.chao.weblog.pojo.User;
import com.chao.weblog.service.AdminService;
import com.chao.weblog.utils.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminService adminService;


    @PostMapping("/login")
    public Object login(String username, String password, HttpSession session, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        User user = adminService.checkUser(username, MD5Utils.code(password));
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("admin", user);
            session.setAttribute("time", new Date());
            logger.warn("登录成功：管理员：{}", user.getNikeName());
            if (session.getAttribute("times") != null) {
                session.removeAttribute("times");
            }
            return "redirect:/admin/index";
        } else {
            if (session.getAttribute("times") == null) {
                session.setAttribute("times", 1);
            } else {
                session.setAttribute("times", (int) session.getAttribute("times") + 1);
            }
            logger.warn("登录失败：登录者：{},连续尝试第{}次", request.getRemoteHost(), session.getAttribute("times"));
            redirectAttributes.addFlashAttribute("message", "账号或密码错误！");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        User admin = (User) session.getAttribute("admin");
        Date oldDate = (Date) session.getAttribute("time");
        Date newDate = new Date();
        logger.warn("管理员退出：管理员：{},在线时长：{}秒", admin.getNikeName(), (newDate.getTime() - oldDate.getTime())/1000 );
        session.removeAttribute("admin");
        return "admin/login";
    }

}
