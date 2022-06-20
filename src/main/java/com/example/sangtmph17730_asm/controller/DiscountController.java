package com.example.sangtmph17730_asm.controller;

import com.example.sangtmph17730_asm.bean.CategoryModel;
import com.example.sangtmph17730_asm.entities.Category;
import com.example.sangtmph17730_asm.entities.Discount;
import com.example.sangtmph17730_asm.repository.CategoryRepository;
import com.example.sangtmph17730_asm.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/discount")
public class DiscountController {

    @Autowired
    DiscountRepository discountRepository;

    @GetMapping("/index")
    public String index
            (
                    @ModelAttribute("dis") Discount dis,
                        Model model,
                        @RequestParam(name="page", defaultValue="0") Integer page,
                        @RequestParam(name="size", defaultValue="5") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC ,"id"));
        Page<Discount> data = discountRepository.findAll(pageable);
        model.addAttribute("formInp", "/view/admin/discount/create.jsp");
        model.addAttribute("table", "/view/admin/discount/table.jsp");
        model.addAttribute("data" , data);
        return "admin/layout";
    }
//
//    @PostMapping("/store")
//    public String store(
//            @Valid @ModelAttribute("cate") CategoryModel cate,
//            BindingResult result,
//            Model model,
//            @RequestParam(name="page", defaultValue="0") Integer page,
//            @RequestParam(name="size", defaultValue="5") Integer size
//    ) {
//        if (result.hasErrors()) {
//            System.out.println("lỗi");
//            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC ,"id"));
//            Page<Category> data = categoryRepository.findAll(pageable);
//            model.addAttribute("formInp", "/view/admin/category/create.jsp");
//            model.addAttribute("table", "/view/admin/category/table.jsp");
//            model.addAttribute("data" , data);
//            return "admin/layout";
//        }
//        Category category = new Category();
//        category.setName(cate.getName());
//        categoryRepository.save(category);
//        return "redirect:/admin/category/index";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable("id") Category cate) {
//        System.out.println(cate.getName());
//        categoryRepository.delete(cate);
//        return "redirect:/admin/category/index";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String edit(
//            @PathVariable("id") Category cate,
//            Model model,
//            @RequestParam(name="page", defaultValue="0") Integer page,
//            @RequestParam(name="size", defaultValue="5") Integer size
//            ) {
//        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC ,"id"));
//        Page<Category> data = categoryRepository.findAll(pageable);
//        model.addAttribute("formInp", "/view/admin/category/edit.jsp");
//        model.addAttribute("table", "/view/admin/category/table.jsp");
//        model.addAttribute("data" , data);
//        model.addAttribute("cate", cate);
//        return "admin/layout";
//    }
//
//    @PostMapping("/update/{id}")
//    public String update(
//            @PathVariable(name = "id") Category cate,
//            @Valid @ModelAttribute("cate") CategoryModel cateModel,
//            BindingResult result,
//            Model model,
//            @RequestParam(name="page", defaultValue="0") Integer page,
//            @RequestParam(name="size", defaultValue="5") Integer size
//    ) {
//        if (result.hasErrors()) {
//            System.out.println("lỗi");
//            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC ,"id"));
//            Page<Category> data = categoryRepository.findAll(pageable);
//            model.addAttribute("formInp", "/view/admin/category/create.jsp");
//            model.addAttribute("table", "/view/admin/category/table.jsp");
//            model.addAttribute("data" , data);
//            return "admin/layout";
//        }
//        cate.setName(cateModel.getName());
//        categoryRepository.save(cate);
//        return "redirect:/admin/category/index";
//    }
//
//    @GetMapping("/api")
//    @ResponseBody
//    public List<Category> categoryList() {
//        return categoryRepository.findAll();
//    }
}
