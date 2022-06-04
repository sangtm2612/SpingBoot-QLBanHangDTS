<%--
  Created by IntelliJ IDEA.
  User: TranSang
  Date: 6/3/2022
  Time: 3:59 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<div>
    <div class="mb-3 row">
        <div class="col-auto me-auto">
            <div>Id order: <span class="fw-bold">${order.id}</span></div>
            <div>Create date: <span class="fw-bold"><fmt:formatDate pattern ="hh:mm:ss dd/MM/yyyy" value ="${order.createDate}"/></span></div>
            <div>Staff: <span class="fw-bold text-primary">${order.account.username}</span></div>
            <div>Status: <span class="fw-bold ${order.status == 0 ? 'text-secondary' : order.status == 1 ? 'text-warning' : 'text-success'}">${order.status == 0 ? 'Prepare' : order.status == 1 ? 'Transport' : 'Complete'}</span></div>
        </div>
        <div class="col-auto">
            <div>Customer: <span class="fw-bold text-success">${order.fullname}</span></div>
            <div>Phone: <span class="fw-bold">${order.phone}</span></div>
            <div>Address: <span class="fw-bold">${order.address}</span></div>
            <div>Total: <span class="fw-bold text-danger"><fmt:formatNumber type="number" pattern="##,###VNĐ" value="${order.total}"/></span></div>
        </div>
    </div>
    <table id="ngu" class="table table-primary table-bordered text-center">
        <thead>
        <tr>
            <th>Product</th>
            <td>Image</td>
            <td>Price</td>
            <td>Quantity</td>
            <td>Total</td>
            <td colspan="2">Manipulation</td>
        </tr>
        </thead>
        <c:forEach items="${ order.orderDetails}" var="o">
            <tr>
                <td>${o.product.name}</td>
                <td><img src="${o.product.image}" width="75"/></td>
                <td class="fw-bold text-danger"><fmt:formatNumber type="number" pattern="##,###VNĐ" value="${o.price}"/></td>
                <td>${o.quantity}</td>
                <td class="fw-bold text-danger"><fmt:formatNumber type="number" pattern="##,###VNĐ" value="${o.price * o.quantity}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
