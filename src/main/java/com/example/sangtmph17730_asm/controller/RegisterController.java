package com.example.sangtmph17730_asm.controller;

import com.example.sangtmph17730_asm.bean.LoginModel;
import com.example.sangtmph17730_asm.entities.Account;
import com.example.sangtmph17730_asm.repository.AccountRepository;
import com.example.sangtmph17730_asm.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    HttpSession session;

    @GetMapping("/register")
    public String index(@ModelAttribute("acc") LoginModel acc) {
        return "register/index";
    }

}
