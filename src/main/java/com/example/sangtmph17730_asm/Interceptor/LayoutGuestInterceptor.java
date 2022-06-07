package com.example.sangtmph17730_asm.Interceptor;

import com.example.sangtmph17730_asm.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LayoutGuestInterceptor implements HandlerInterceptor {

    @Autowired
    private HttpSession session;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        request.setAttribute("head", "/view/guest/layout/header.jsp");
//        request.setAttribute("navbarr", "/view/guest/layout/navbar.jsp");
//        request.setAttribute("contentt", "/view/guest/layout/content.jsp");
//        request.setAttribute("foot", "/view/guest/layout/footer.jsp");
//        request.setAttribute("listCategory", categoryRepository.findAll());
        return true;
    }
}
