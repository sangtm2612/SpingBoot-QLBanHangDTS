package com.example.sangtmph17730_asm.controller;

import com.example.sangtmph17730_asm.bean.AccountModel;
import com.example.sangtmph17730_asm.bean.ProductModel;
import com.example.sangtmph17730_asm.entities.Account;
import com.example.sangtmph17730_asm.entities.Category;
import com.example.sangtmph17730_asm.entities.Product;
import com.example.sangtmph17730_asm.repository.*;
import com.example.sangtmph17730_asm.services.AccountServiceImp;
import com.example.sangtmph17730_asm.utils.ExportUtil;
import org.apache.naming.java.javaURLContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    ServletContext app;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;


    @GetMapping("/index")
    public String index(
                    @ModelAttribute("pro") ProductModel pro,
                    Model model,
                    @RequestParam(name="page", defaultValue="0") Integer page,
                    @RequestParam(name="size", defaultValue="5") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC ,"id"));
        Page<Product> data = productRepository.findAll(pageable);
        model.addAttribute("formInp", "/view/admin/product/create.jsp");
        model.addAttribute("table", "/view/admin/product/table.jsp");
        model.addAttribute("data" , data);
        model.addAttribute("listCate", categoryList());
        return "admin/layout";
    }

    @GetMapping("/export")
    public String export() {
        try {
            ExportUtil.writeExcel(productRepository.findAll(), app.getRealPath("/file/excel/product.xlsx"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "admin/layout";
    }

    @PostMapping("/store")
    public String store(
            @Valid @ModelAttribute("pro") ProductModel pro,
            @RequestParam("attach")MultipartFile attach,
            Model model,
            BindingResult result,
            @RequestParam(name="page", defaultValue="0") Integer page,
            @RequestParam(name="size", defaultValue="5") Integer size
    ) {
        if (result.hasErrors()) {
            System.out.println("ngu");
            Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC ,"id"));
            Page<Product> data = productRepository.findAll(pageable);
            model.addAttribute("formInp", "/view/admin/product/create.jsp");
            model.addAttribute("table", "/view/admin/product/table.jsp");
            model.addAttribute("data" , data);
            model.addAttribute("listCate", categoryList());
            return "admin/layout";
        }
        Product product = new Product();
        product.setCreatedDate(new Date());
        product.setImage("");
        product.setPrice(pro.getPrice());
        product.setAvailable(pro.getAvailable());
        product.setName(pro.getName());
        product.setCategory(categoryRepository.getById(pro.getCategoryId()));
        if (!attach.isEmpty()) {
            String filename = attach.getOriginalFilename();
            File file = new File(app.getRealPath("/files/" + filename));
            product.setImage("/files/" + filename);
            try {
                attach.transferTo(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        productRepository.save(product);
        return "redirect:/admin/product/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Product pro) {
        productRepository.delete(pro);
        return "redirect:/admin/product/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Product pro,
            Model model,
            @RequestParam(name="page", defaultValue="0") Integer page,
            @RequestParam(name="size", defaultValue="5") Integer size
            ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC ,"id"));
        Page<Product> data = productRepository.findAll(pageable);
        model.addAttribute("formInp", "/view/admin/product/edit.jsp");
        model.addAttribute("table", "/view/admin/product/table.jsp");
        model.addAttribute("data" , data);
        model.addAttribute("listCate", categoryList());
        model.addAttribute("pro", pro);
        return "admin/layout";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Product pro, ProductModel productModel,  @RequestParam("attach")MultipartFile attach) {
        pro.setImage("");
        pro.setPrice(productModel.getPrice());
        pro.setAvailable(productModel.getAvailable());
        pro.setName(productModel.getName());
        if (!attach.isEmpty()) {
            String filename = attach.getOriginalFilename();
            File file = new File(app.getRealPath("/files/product/" + filename));
            pro.setImage("/files/product/" + filename);
            try {
                attach.transferTo(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        productRepository.save(pro);
        return "redirect:/admin/product/index";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Category> categoryList() {
        return categoryRepository.findAll();
    }
}
