package com.example.sangtmph17730_asm.controller;

import com.example.sangtmph17730_asm.bean.AccountModel;
import com.example.sangtmph17730_asm.bean.CategoryModel;
import com.example.sangtmph17730_asm.entities.Account;
import com.example.sangtmph17730_asm.entities.Category;
import com.example.sangtmph17730_asm.repository.AccountRepository;
import com.example.sangtmph17730_asm.repository.CategoryRepository;
import com.example.sangtmph17730_asm.services.AccountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin/account")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;

    AccountServiceImp accountServiceImp = new AccountServiceImp();

    @GetMapping("/index")
    public String index(
                    @ModelAttribute("acc") AccountModel acc,
                    Model model,
                    @RequestParam(name="page", defaultValue="0") Integer page,
                    @RequestParam(name="size", defaultValue="5") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Account> data = accountRepository.findAll(pageable);
        model.addAttribute("formInp", "/view/admin/account/create.jsp");
        model.addAttribute("table", "/view/admin/account/table.jsp");
        model.addAttribute("data" , data);
        return "admin/layout";
    }

    @PostMapping("/store")
    public String store(AccountModel acc) {
        Account account = new Account();
        account.setFullname(acc.getFullname());
        account.setActivated(acc.getActivated());
        account.setAdmin(acc.getAdmin());
        account.setEmail(acc.getEmail());
        account.setPassword(acc.getPassword());
        account.setPhoto("Chưa làm");
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
        Pageable pageable = PageRequest.of(page, size);
        Page<Account> data = accountRepository.findAll(pageable);
        model.addAttribute("formInp", "/view/admin/account/edit.jsp");
        model.addAttribute("table", "/view/admin/account/table.jsp");
        model.addAttribute("data" , data);
        model.addAttribute("acc", acc);
        return "admin/layout";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Account acc, AccountModel accModel) {
        acc.setUsername(accModel.getUsername());
        acc.setFullname(accModel.getFullname());
        acc.setActivated(accModel.getActivated());
        acc.setAdmin(accModel.getAdmin());
        acc.setEmail(accModel.getEmail());
        accountRepository.save(acc);
        return "redirect:/admin/account/index";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Account> accountsList() {
        return accountRepository.findAll();
    }
}
