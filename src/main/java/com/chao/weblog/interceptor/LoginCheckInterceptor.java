package com.chao.weblog.interceptor;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("admin");
        if (user == null) {
            LoggerFactory.getLogger(this.toString()).warn("非法访问：访问者：{}，请求：{}", request.getRemoteHost(), request.getRequestURL());
            request.setAttribute("message", "权限不足请先登录！");
            request.getRequestDispatcher("/admin").forward(request,response);
            return false;
        }
        return true;
    }
}
