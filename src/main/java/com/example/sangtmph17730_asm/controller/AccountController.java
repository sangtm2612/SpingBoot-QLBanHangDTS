package com.example.sangtmph17730_asm.controller;

import com.example.sangtmph17730_asm.bean.AccountModel;
import com.example.sangtmph17730_asm.bean.CategoryModel;
import com.example.sangtmph17730_asm.entities.Account;
import com.example.sangtmph17730_asm.entities.Category;
import com.example.sangtmph17730_asm.repository.AccountRepository;
import com.example.sangtmph17730_asm.repository.CategoryRepository;
import com.example.sangtmph17730_asm.services.AccountServiceImp;
import com.example.sangtmph17730_asm.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin/account")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;

    AccountServiceImp accountServiceImp = new AccountServiceImp();

    @Autowired
    ServletContext app;

    @GetMapping("/index")
    public String index(
                    @ModelAttribute("acc") AccountModel acc,
                    Model model,
                    @RequestParam(name="page", defaultValue="0") Integer page,
                    @RequestParam(name="size", defaultValue="5") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC ,"id"));
        Page<Account> data = accountRepository.findAll(pageable);
        model.addAttribute("formInp", "/view/admin/account/create.jsp");
        model.addAttribute("table", "/view/admin/account/table.jsp");
        model.addAttribute("data" , data);
        return "admin/layout";
    }

    @PostMapping("/store")
    public String store(AccountModel acc, @RequestParam("attach") MultipartFile attach) {
        Account account = new Account();
        account.setFullname(acc.getFullname());
        account.setActivated(acc.getActivated());
        account.setAdmin(acc.getAdmin());
        account.setEmail(acc.getEmail());
        account.setPassword(EncryptUtil.encrypt(acc.getPasswordConfirm()));
        if (!attach.isEmpty()) {
            String filename = attach.getOriginalFilename();
            File file = new File(app.getRealPath("/files/account/" + filename));
            account.setPhoto("/files/account/" + filename);
            try {
                attach.transferTo(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        account.setUsername(acc.getUsername());
        accountRepository.save(account);
        return "redirect:/admin/account/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Account acc) {
        accountRepository.delete(acc);
        return "redirect:/admin/account/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Account acc,
            Model model,
            @RequestParam(name="page", defaultValue="0") Integer page,
            @RequestParam(name="size", defaultValue="5") Integer size
            ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC ,"id"));
        Page<Account> data = accountRepository.findAll(pageable);
        model.addAttribute("formInp", "/view/admin/account/edit.jsp");
        model.addAttribute("table", "/view/admin/account/table.jsp");
        model.addAttribute("data" , data);
        model.addAttribute("acc", acc);
        return "admin/layout";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Account acc, AccountModel accModel,  @RequestParam("attach") MultipartFile attach) {
        acc.setUsername(accModel.getUsername());
        acc.setFullname(accModel.getFullname());
        acc.setActivated(accModel.getActivated());
        acc.setAdmin(accModel.getAdmin());
        acc.setEmail(accModel.getEmail());
        if (!attach.isEmpty()) {
            String filename = attach.getOriginalFilename();
            File file = new File(app.getRealPath("/files/account/" + filename));
            acc.setPhoto("/files/account/" + filename);
            try {
                attach.transferTo(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        accountRepository.save(acc);
        return "redirect:/admin/account/index";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Account> accountsList() {
        return accountRepository.findAll();
    }
}
