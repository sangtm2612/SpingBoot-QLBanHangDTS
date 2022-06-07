package com.example.sangtmph17730_asm.controller;

import com.example.sangtmph17730_asm.bean.CategoryModel;
import com.example.sangtmph17730_asm.bean.LoginModel;
import com.example.sangtmph17730_asm.entities.Account;
import com.example.sangtmph17730_asm.entities.Category;
import com.example.sangtmph17730_asm.repository.AccountRepository;
import com.example.sangtmph17730_asm.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    HttpSession session;

    @GetMapping("/login")
    public String index(@ModelAttribute("acc") LoginModel acc) {
        return "login/login";
    }

    @PostMapping("/login/hi")
    public String login(LoginModel model) {
        Account acc = accountRepository.findByUsernameAndPassword(model.getUsername(), model.getPassword());
        session.setAttribute("user", acc);
        if (acc == null) {
            session.setAttribute("error", "Login fail!");
            return "redirect:/home/login";
        }
        return "redirect:/admin/product/index";
    }

    @GetMapping("/logout")
    public String logout() {
        session.removeAttribute("user");
        return "redirect:/login";
    }
}
