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
                <td>${o.price}</td>
                <td>${o.quantity}</td>
                <td>${o.price * o.quantity}</td>
            </tr>
        </c:forEach>
    </table>
</div>
