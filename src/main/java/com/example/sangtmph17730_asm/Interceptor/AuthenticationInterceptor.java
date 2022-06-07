package com.example.sangtmph17730_asm.Interceptor;

import com.example.sangtmph17730_asm.entities.Account;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            request.setAttribute("bannerr", "/view/layout/header.jsp");
            request.setAttribute("menuTop", "/view/layout/navbar.jsp");
            return true;
        }
        response.sendRedirect("/login");
        return false;
    }
}

