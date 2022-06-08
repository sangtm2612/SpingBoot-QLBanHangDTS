package com.example.sangtmph17730_asm.controller;

import com.example.sangtmph17730_asm.bean.OrderDetailModel;
import com.example.sangtmph17730_asm.bean.OrderModel;
import com.example.sangtmph17730_asm.bean.ProductModel;
import com.example.sangtmph17730_asm.entities.*;
import com.example.sangtmph17730_asm.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC ,"id"));
        Page<Order> data = orderRepository.findAll(pageable);
        model.addAttribute("formInp", "/view/admin/order/create.jsp");
        model.addAttribute("table", "/view/admin/order/table.jsp");
        model.addAttribute("data" , data);
        return "admin/layout";
    }

    @GetMapping("/status/update/{status}/{id}")
    public String updateStatus(
        @PathVariable("status") int status,
        @PathVariable("id") Order o
    ) {
        if (status == 1) {
            Account acc = (Account) session.getAttribute("user");
            o.setAccount(acc);
        }
        o.setStatus(status);
        orderRepository.save(o);
        session.setAttribute("message", "Order " + o.getId() + " update successfuly!");
        return "redirect:/admin/order/index";
    }

    @GetMapping("/search")
    public String search(
            @ModelAttribute("order") OrderModel order,
            Model model,
            @RequestParam(name="page", defaultValue="0") Integer page,
            @RequestParam(name="size", defaultValue="10") Integer size
    ) {
        String value = (String) model.getAttribute("orderSearch");
        System.out.println(value);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC ,"id"));
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
        Account acc = (Account) session.getAttribute("user");
        orderDetails = new ArrayList<>();
        Order o = new Order();
        o.setAccount(acc);
        o.setTotal(0);
        o.setCreateDate(new Date());
        o.setAddress("");
        o.setFullname("");
        o.setPhone("");
        o.setStatus(0);
        Order order = orderRepository.save(o);
        session.setAttribute("order", order);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC ,"id"));
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
                Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC ,"id"));
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
        session.setAttribute("message", "Added product " + pro.getName() + " to cart!");
        model.addAttribute("listOD", orderDetails);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC ,"id"));
        Page<Product> data = productRepository.findAll(pageable);
        model.addAttribute("formInp", "/view/admin/order/createOrder/create.jsp");
        model.addAttribute("table", "/view/admin/order/createOrder/tableProduct.jsp");
        model.addAttribute("data", data);
        return "admin/layout";
    }

    @GetMapping("/view/{id}")
    public String view(
            @PathVariable("id") Order o,
            @ModelAttribute("order") OrderModel order,
            Model model
    ) {
        model.addAttribute("order", o);
        model.addAttribute("formInp", "/view/admin/page.jsp");
        model.addAttribute("table", "/view/admin/orderDetail/view.jsp");
        return "admin/layout";
    }

    @GetMapping("/{idO}/orderdetail/delete/{idOD}")
    public String deleteOrderDetail(
            @PathVariable("idOD") OrderDetail od,
            @PathVariable("idO") Order o,
            Model model
    ) {
        orderDetailRepository.delete(od);
        o.setTotal(getTotalOrder(o.getOrderDetails()));
        orderRepository.save(o);
        session.setAttribute("message", "Successful delete order detail!");
        return "redirect:/admin/order/edit/" + o.getId();
    }

    @GetMapping("/{idO}/orderdetail/update/{idOD}")
    public String updateOrderDetail(
            @PathVariable("idOD") OrderDetail od,
            @PathVariable("idO") Order o,
            OrderDetailModel orderDetailModel,
            Model model
    ) {
        od.setQuantity(orderDetailModel.getQuantity());
        orderDetailRepository.save(od);
        o.setTotal(getTotalOrder(o.getOrderDetails()));
        orderRepository.save(o);
        session.setAttribute("message", "Successful update order detail!");
        return "redirect:/admin/order/edit/" + o.getId();
    }


    @PostMapping("/store")
    public String store(Model model, OrderModel orderModel) {
        Order o = (Order) session.getAttribute("order");
        o.setPhone(orderModel.getPhone());
        o.setFullname(orderModel.getFullname());
        o.setAddress(orderModel.getAddress());
        o.setTotal(getTotalOrder());
        Order od = orderRepository.save(o);
        model.addAttribute("order", od);
        return "redirect:/admin/order/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Order o) {
        if (o.getStatus() != 0) {
            session.setAttribute("error", "Can not delete!");
            return "redirect:/admin/order/index";
        } else {
            session.setAttribute("message", "Successful delete " + o.getId() + "!");
        }
        orderRepository.delete(o);
        return "redirect:/admin/order/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Order o,
            @ModelAttribute("orderDetail") OrderDetailModel orderDetail,
            Model model,
            @RequestParam(name="page", defaultValue="0") Integer page,
            @RequestParam(name="size", defaultValue="10") Integer size
            ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC ,"id"));
        Page<Product> data = productRepository.findAll(pageable);
        model.addAttribute("order", o);
        model.addAttribute("formInp", "/view/admin/page.jsp");
        model.addAttribute("table", "/view/admin/orderDetail/edit.jsp");
        model.addAttribute("data", data);
        return "admin/layout";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Order o, OrderModel orderModel) {
        o.setFullname(orderModel.getFullname());
        o.setAddress(orderModel.getAddress());
        o.setPhone(orderModel.getPhone());
        orderRepository.save(o);
        session.setAttribute("message", "Successful update infor!");
        return "redirect:/admin/order/edit/" + o.getId();
    }

    @GetMapping("/{idO}/add/{idP}")
    public String addProductToOrder(
            @PathVariable(name = "idO") Order o,
            @PathVariable(name = "idP") Product p,
            @RequestParam(name = "quantity") int i,
            Model model
    ) {
        for(OrderDetail od : o.getOrderDetails()) {
            if (od.getProduct().getId() == p.getId()) {
                od.setQuantity(od.getQuantity() + i);
                orderDetailRepository.save(od);
                o.setTotal(getTotalOrder(o.getOrderDetails()));
                orderRepository.save(o);
                session.setAttribute("message", "Successful add!");
                return "redirect:/admin/order/edit/" + o.getId();
            }
        }
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(o);
        orderDetail.setProduct(p);
        orderDetail.setPrice(p.getPrice());
        orderDetail.setQuantity(i);
        orderDetailRepository.save(orderDetail);
        session.setAttribute("message", "Successful add!");
        return "redirect:/admin/order/edit/" + o.getId();
    }

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

    public int getTotalOrder(List<OrderDetail> orderDetails) {
        int sum = 0;
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
