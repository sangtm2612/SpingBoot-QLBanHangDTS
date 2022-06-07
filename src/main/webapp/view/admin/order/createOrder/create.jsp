<%--
  Created by IntelliJ IDEA.
  User: TranSang
  Date: 6/2/2022
  Time: 8:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="i" scope="session" value="0"/>
<div>
    <button class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">Order detail <i class="bi bi-cart"></i></button>

    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title text-center w-100" id="exampleModalLabel">Order Detail</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <table class="table text-center">
                        <thead>
                        <tr class="table-dark">
                            <th>Product</th>
                            <td>Image</td>
                            <td>Price</td>
                            <td>Quantity</td>
                            <td>Total</td>
                            <td colspan="2">Manipulation</td>
                        </tr>
                        </thead>
                        <c:forEach items="${ listOD}" var="o">
                            <tr>
                                <td>${o.product.name}</td>
                                <td><img src="${o.product.image}" width="75"/></td>
                                <td><span class="text-danger fw-bold"><fmt:formatNumber type="number" pattern="##,###VNĐ" value="${o.price}"/></span></td>
                                <td>${o.quantity}</td>
                                <td><span class="text-danger fw-bold"><fmt:formatNumber type="number" pattern="##,###VNĐ" value="${o.price * o.quantity}"/></span></td>
                                <td><a class="btn btn-primary" href="/admin/order/edit/${order.id}" >Edit quantity</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="row mt-3">
                        <div class="col-6">
                            <%--@elvariable id="o" type="lombok"--%>
                            <form:form action="/admin/order/store" method="post" modelAttribute="o">
                                <div class="mt-3">
                                    <form:label path="fullname" class="form-lable">Fullname:</form:label>
                                    <form:input path="fullname" name="fullname" class="form-control"/>
                                </div>
                                <div class="mt-3">
                                    <form:label path="phone" class="form-lable">Phone:</form:label>
                                    <form:input path="phone" name="phone" class="form-control"/>
                                </div>
                                <div class="mt-3">
                                    <form:label path="address" class="form-lable">Address:</form:label>
                                    <form:input path="address" name="address" class="form-control"/>
                                </div>
                                <button type="submit" class="btn btn-primary mt-3">Payment</button>
                            </form:form>
                        </div>
                        <div class="col-6 mt-3">Total order: <span class="text-danger fw-bold"><fmt:formatNumber type="number" pattern="##,###VNĐ" value="${totalOrder}"/></span></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
