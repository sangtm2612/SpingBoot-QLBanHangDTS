package com.example.sangtmph17730_asm.controller;

import com.example.sangtmph17730_asm.bean.CategoryModel;
import com.example.sangtmph17730_asm.bean.OrderDetailModel;
import com.example.sangtmph17730_asm.bean.OrderModel;
import com.example.sangtmph17730_asm.entities.Category;
import com.example.sangtmph17730_asm.entities.Order;
import com.example.sangtmph17730_asm.entities.OrderDetail;
import com.example.sangtmph17730_asm.entities.Product;
import com.example.sangtmph17730_asm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/home/guest")
public class GuestController {

    List<OrderDetail> orderDetails = new ArrayList<>();

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    HttpSession session;

    @GetMapping("/product")
    public String index(
            Model model,
            @ModelAttribute("orderDetail") OrderDetailModel orderDetailModel,
            @RequestParam(name="page", defaultValue="0") Integer page,
            @RequestParam(name="size", defaultValue="20") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC ,"id"));
        Page<Product> data = productRepository.findAll(pageable);
        Order order = new Order();
        session.setAttribute("order", order);
        model.addAttribute("data" , data);
        return "guest/layout";
    }

    @PostMapping("/cart/product/{id}")
    public String addToCart(
            Model model,
            OrderDetailModel orderDetailModel,
            @PathVariable("id") Product pro
    ) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(pro);
        orderDetail.setPrice(pro.getPrice());
        orderDetail.setQuantity(orderDetailModel.getQuantity());
        orderDetails.add(orderDetail);
        session.setAttribute("message", "Add to cart successfully");
        return "redirect:/home/guest/product";
    }

    @GetMapping("/cart")
    public String cart(
            Model model,
            @ModelAttribute("od") OrderDetailModel orderDetailModel,
            @ModelAttribute("order") OrderModel orderModel
            ) {
        model.addAttribute("contentt", "/view/guest/cart.jsp");
        session.setAttribute("orderDetails", orderDetails);
        model.addAttribute("total", getTotalOrder(orderDetails));
        return "guest/layout";
    }

    @GetMapping("/category/{id}/product")
    public String productByCategory(
            @PathVariable(name = "id") Category cate,
            Model model,
            @RequestParam(name="page", defaultValue="0") Integer page,
            @RequestParam(name="size", defaultValue="20") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC ,"id"));
        Page<Product> data = productRepository.findAll(pageable);
        model.addAttribute("data" , data);

        return "guest/layout";
    }

    @PostMapping("/cart/order")
    public String order(
            Order o
    ) {
        o.setTotal(getTotalOrder(orderDetails));
        o.setCreateDate(new Date());
        o.setStatus(0);
        orderRepository.save(o);
        for (OrderDetail od : orderDetails) {
            od.setOrder(o);
            orderDetailRepository.save(od);
        }
        orderRepository.save(o);
        return "redirect:/home/guest/order/view/" + o.getId();
    }

//    @PostMapping("/order/update/quantity/{id}")
//    public String updateQuantity(
//            @PathVariable("id") OrderDetail orderDetail,
//            OrderDetailModel orderDetailModel
//    ) {
//        return "redirect:/home/guest/order/view/" + o.getId();
//    }

    @GetMapping("/order/view/{id}")
    public String view(
            @PathVariable("id") Order o,
            Model model
    ) {
        model.addAttribute("contentt", "/view/guest/view.jsp");
        model.addAttribute("order", o);
        return "guest/layout";
    }

    @GetMapping("/order/delete/{id}")
    public String delete(
        @PathVariable("id") Order order
    ) {
        for(OrderDetail od : order.getOrderDetails()) {
            orderDetailRepository.delete(od);
        }
        orderRepository.delete(order);
        session.setAttribute("message", "Successfully deleted order " + order.getId() +"!");
        return "redirect:/home/guest/product";
    }

    public int getTotalOrder(List<OrderDetail> orderDetails) {
        int sum = 0;
        for (OrderDetail o : orderDetails) {
            sum += o.getQuantity() * o.getPrice();
        }
        return sum;
    }
}
