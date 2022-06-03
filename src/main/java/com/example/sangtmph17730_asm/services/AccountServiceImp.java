package com.example.sangtmph17730_asm.services;

import com.example.sangtmph17730_asm.bean.AccountModel;
import com.example.sangtmph17730_asm.entities.Account;
import com.example.sangtmph17730_asm.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AccountServiceImp implements AccountService{

    @Autowired
    AccountRepository accountRepository;

    public void getIndex(
            AccountModel acc,
            Model model,
            Integer page,
            Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Account> data = accountRepository.findAll(pageable);
        model.addAttribute("formInp", "/view/admin/account/create.jsp");
        model.addAttribute("table", "/view/admin/account/table.jsp");
        model.addAttribute("data" , data);
    }
}
