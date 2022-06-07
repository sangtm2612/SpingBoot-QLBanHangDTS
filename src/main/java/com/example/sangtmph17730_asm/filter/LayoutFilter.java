package com.example.sangtmph17730_asm.filter;

import com.example.sangtmph17730_asm.entities.Account;
import com.example.sangtmph17730_asm.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.naming.Name;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Order(1)
@WebFilter(filterName = "adminFilter",urlPatterns = {"/home/guest/*"})
public class LayoutFilter implements Filter {

    @Autowired
    HttpSession session;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setAttribute("head", "/view/guest/layout/header.jsp");
        request.setAttribute("navbarr", "/view/guest/layout/navbar.jsp");
        request.setAttribute("contentt", "/view/guest/layout/content.jsp");
        request.setAttribute("foot", "/view/guest/layout/footer.jsp");
        request.setAttribute("listCategory", categoryRepository.findAll());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
