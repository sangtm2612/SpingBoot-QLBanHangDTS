<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-sm navbar-expand-sm navbar-dark bg-danger">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto w-100 mb-2 mb-lg-0 d-flex justify-content-center">
                <li class="nav-item border border-light rounded mx-1">
                    <a class="nav-link " aria-current="page" href="/admin/category/index">Category</a>
                </li>
                <li class="nav-item border border-light rounded mx-1">
                    <a class="nav-link" href="/admin/product/index">Product</a>
                </li>
                <li class="nav-item border border-light rounded mx-1">
                    <a class="nav-link" href="/admin/account/index">Account</a>
                </li>
                <li class="nav-item border border-light rounded mx-1">
                    <a class="nav-link" href="/admin/order/index">Order</a>
                </li>
                <li class="nav-item rounded mx-1 position-absolute end-0 d-flex">
                    <img src="${sessionScope.user.photo}" class="rounded-circle me-2 mt-2 border border-white" width="30" height="30">
                    <span class="mt-2 text-white">${sessionScope.user.username}, </span>
                    <a class="nav-link" href="/logout">logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>