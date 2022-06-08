<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
        <div class="mb-3">
            <h1>OrderManager</h1>
            <%--@elvariable id="pro" type="lombok"--%>
            <div class="row">
                <div class="col-auto me-auto">
                    <a class="btn btn-primary" href="/admin/order/createOrder">Create order</a>
                </div>
                <div class="col-2">
                    <form action="/admin/order/search" method="get" class="d-flex">
                        <input type="text" class="form-control form-control-sm mt-1 me-1" name="orderSearch" placeholder="Order search">
                        <button type="submit" class="btn btn-primary btn-sm">Search</button>
                    </form>
                </div>
            </div>
        </div>
