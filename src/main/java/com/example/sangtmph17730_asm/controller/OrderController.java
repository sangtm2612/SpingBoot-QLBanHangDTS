package com.example.sangtmph17730_asm.controller;

import com.example.sangtmph17730_asm.bean.OrderModel;
import com.example.sangtmph17730_asm.bean.ProductModel;
import com.example.sangtmph17730_asm.entities.Category;
import com.example.sangtmph17730_asm.entities.Order;
import com.example.sangtmph17730_asm.entities.OrderDetail;
import com.example.sangtmph17730_asm.entities.Product;
import com.example.sangtmph17730_asm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin/order")
public class OrderController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ServletContext app;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpSession session;

    List<OrderDetail> orderDetails = new ArrayList<>();

    @GetMapping("/index")
    public String index(
                    @ModelAttribute("order") OrderModel order,
                    Model model,
                    @RequestParam(name="page", defaultValue="0") Integer page,
                    @RequestParam(name="size", defaultValue="10") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Order> data = orderRepository.findAll(pageable);
        model.addAttribute("formInp", "/view/admin/order/create.jsp");
        model.addAttribute("table", "/view/admin/order/table.jsp");
        model.addAttribute("data" , data);
        return "admin/layout";
    }

    @GetMapping("/createOrder")
    public String insert(
            Model model,
            @ModelAttribute(name = "o") OrderModel od,
            @RequestParam(name="page", defaultValue="0") Integer page,
            @RequestParam(name="size", defaultValue="5") Integer size
    ) {
        orderDetails = new ArrayList<>();
        Order o = new Order();
        o.setAccount(accountRepository.getById(1));
        o.setTotal(0);
        o.setCreateDate(new Date());
        o.setAddress("");
        o.setFullname("");
        o.setPhone("");
        o.setStatus(0);
        Order order = orderRepository.save(o);
        session.setAttribute("order", order);
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> data = productRepository.findAll(pageable);
        model.addAttribute("formInp", "/view/admin/order/createOrder/create.jsp");
        model.addAttribute("table", "/view/admin/order/createOrder/tableProduct.jsp");
        model.addAttribute("data", data);
        return "admin/layout";
    }

    @GetMapping("/add/{id}")
    public String add(
            @PathVariable("id") Product pro,
            Model model,
            @ModelAttribute(name = "o") OrderModel odM,
            @RequestParam(name="page", defaultValue="0") Integer page,
            @RequestParam(name="size", defaultValue="5") Integer size
    ) {
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int i = -1;
        for (OrderDetail o : orderDetails) {
            i++;
            if (o.getProduct().getId() == pro.getId()) {
                o.setQuantity(o.getQuantity() + quantity);
                orderDetails.set(i, o);
                model.addAttribute("listOD", orderDetails);
                Pageable pageable = PageRequest.of(page, size);
                model.addAttribute("totalOrder", getTotalOrder());
                Page<Product> data = productRepository.findAll(pageable);
                model.addAttribute("formInp", "/view/admin/order/createOrder/create.jsp");
                model.addAttribute("table", "/view/admin/order/createOrder/tableProduct.jsp");
                model.addAttribute("data", data);
                return "admin/layout";
            }
        }
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setQuantity(quantity);
        orderDetail.setProduct(pro);
        orderDetail.setPrice(pro.getPrice());
        Order o = (Order) session.getAttribute("order");
        o.setTotal(o.getTotal() + orderDetail.getPrice() * orderDetail.getQuantity());
        session.setAttribute("order", o);
        orderRepository.save(o);
        orderDetail.setOrder(o);
        OrderDetail od = orderDetailRepository.save(orderDetail);
        orderDetails.add(od);
        model.addAttribute("totalOrder", getTotalOrder());
        session.setAttribute("message", "Success");
        model.addAttribute("listOD", orderDetails);
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> data = productRepository.findAll(pageable);
        model.addAttribute("formInp", "/view/admin/order/createOrder/create.jsp");
        model.addAttribute("table", "/view/admin/order/createOrder/tableProduct.jsp");
        model.addAttribute("data", data);
        return "admin/layout";
    }

    @GetMapping("/view/{id}")
    public String view(
            @PathVariable("id") Order o,
            Model model
    ) {
        model.addAttribute("order", o);
        model.addAttribute("formInp", "/view/admin/page.jsp");
        model.addAttribute("table", "/view/admin/orderDetail/view.jsp");
        return "admin/layout";
    }


    @PostMapping("/store")
    public String store(Model model, OrderModel orderModel) {
        Order o = (Order) session.getAttribute("order");
        o.setPhone(orderModel.getPhone());
        o.setFullname(orderModel.getFullname());
        o.setAddress(orderModel.getAddress());
        o.setTotal(getTotalOrder());
        orderRepository.save(o);
        return "redirect:/admin/order/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Order o) {
        if (o.getStatus() != 0) {
            session.setAttribute("error", "Can not delete!");
            return "redirect:/admin/order/index";
        } else {
            session.setAttribute("message", "Successful delete!");
        }
        orderRepository.delete(o);
        return "redirect:/admin/order/index";
    }
//
//    @GetMapping("/edit/{id}")
//    public String edit(
//            @PathVariable("id") Product pro,
//            Model model,
//            @RequestParam(name="page", defaultValue="0") Integer page,
//            @RequestParam(name="size", defaultValue="5") Integer size
//            ) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Product> data = productRepository.findAll(pageable);
//        model.addAttribute("formInp", "/view/admin/product/edit.jsp");
//        model.addAttribute("table", "/view/admin/product/table.jsp");
//        model.addAttribute("data" , data);
//        model.addAttribute("listCate", categoryList());
//        model.addAttribute("pro", pro);
//        return "admin/layout";
//    }
//
//    @PostMapping("/update/{id}")
//    public String update(@PathVariable(name = "id") Product pro, ProductModel productModel) {
//        pro.setImage("");
//        pro.setPrice(productModel.getPrice());
//        pro.setAvailable(productModel.getAvailable());
//        pro.setName(productModel.getName());
//        productRepository.save(pro);
//        return "redirect:/admin/product/index";
//    }

    public int getTotalOrder() {
        int sum = 0;
        if (orderDetails.size() == 0) {
            return 0;
        }
        for (OrderDetail o : orderDetails) {
            sum += o.getQuantity() * o.getPrice();
        }
        return sum;
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Category> categoryList() {
        return categoryRepository.findAll();
    }
}
