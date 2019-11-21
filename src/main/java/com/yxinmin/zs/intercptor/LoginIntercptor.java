package com.yxinmin.zs.intercptor;

import com.yxinmin.zs.entity.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginIntercptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("执行拦截器");
        User user= (User) request.getSession().getAttribute("user");
        if(user==null){
            PrintWriter out = response.getWriter();
            out.print("fail");
            out.close();
            return false;
        }
        return true;
    }
}
