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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div>
    <c:if test="${!empty sessionScope.message}">
        <div class="alert alert-success mt-3" role="alert">
            ${sessionScope.message}
        </div>
        <c:remove var="message" scope="session"></c:remove>
    </c:if>
    <div class="mb-3 row">
        <div class="col-auto ms-2 me-auto">
            <%--@elvariable id="order" type="lombok"--%>
            <form:form action="/admin/order/update/${order.id}" method="post" modelAttribute="order">
                <div class="">
                    <form:label path="fullname">Customer:</form:label>
                    <form:input class="form-control form-control-sm" path="fullname" value=""></form:input>
                </div>
                <div>
                    <form:label path="phone">Phone:</form:label>
                    <form:input class="form-control form-control-sm" path="phone" value=""></form:input>
                </div>
                <div>
                    <form:label path="address">Address:</form:label>
                    <form:input class="form-control form-control-sm" path="address" value=""></form:input>
                </div>
                <form:button class="btn btn-primary mt-2">Update infor</form:button>
            </form:form>
        </div>
        <div class="col-auto">
            <div>Id order: <span class="fw-bold">${order.id}</span></div>
            <div>Create date: <span class="fw-bold"><fmt:formatDate pattern ="hh:mm:ss dd/MM/yyyy" value ="${order.createDate}"/></span></div>
            <div>Staff: <span class="fw-bold text-primary">${order.account.username}</span></div>
            <div>Status: <span class="fw-bold ${order.status == 0 ? 'text-secondary' : order.status == 1 ? 'text-warning' : 'text-success'}">${order.status == 0 ? 'Prepare' : order.status == 1 ? 'Transport' : 'Complete'}</span></div>
            <div>Total: <span class="fw-bold text-danger"><fmt:formatNumber type="number" pattern="##,###VNĐ" value="${order.total}"/></span></div>
        </div>
    </div>
    <table id="ngu" class="table text-center">
        <thead>
        <tr>
            <th>Product</th>
            <th>Image</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Total</th>
            <th colspan="2">Manipulation</th>
        </tr>
        </thead>
        <c:forEach items="${ order.orderDetails}" var="o">
            <tr>
                <td>${o.product.name}</td>
                <td><img src="${o.product.image}" width="75"/></td>
                <td class="fw-bold text-danger"><fmt:formatNumber type="number" pattern="##,###VNĐ" value="${o.price}"/></td>
                <td>${o.quantity}</td>
                <td class="fw-bold text-danger"><fmt:formatNumber type="number" pattern="##,###VNĐ" value="${o.price * o.quantity}"/></td>
                <td><a class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal${o.id}">Edit</a></td>

                <!-- Modal -->
                <div class="modal fade" id="exampleModal${o.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content"><%--@elvariable id="orderDetail" type="lombok"--%>
                            <form:form action="/admin/order/${order.id}/orderdetail/update/${o.id}" method="get" modelAttribute="orderDetail">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Modal edit</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                    <form:label path="quantity">Quantity:</form:label>
                                    <form:input type="number" cssClass="form-control" value="${o.quantity}" path="quantity"></form:input>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary">Update</button>
                            </div>
                            </form:form>
                        </div>
                    </div>
                </div>

                <td><a class="btn btn-danger"  data-bs-toggle="modal" data-bs-target="#modal${o.id}">Delete</a></td>

                <!-- Modal -->
                <div class="modal fade" id="modal${o.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="modalDelete${o.id}">Message</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                Do you want to delete?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <a class="btn btn-danger" href="/admin/order/${order.id}/orderdetail/delete/${o.id}">Confirm</a>
                            </div>
                        </div>
                    </div>
                </div>
            </tr>
        </c:forEach>
    </table>

    <button class="btn btn-outline-primary w-100" data-bs-toggle="modal" data-bs-target="#md"><i class="bi bi-plus-circle"></i></button>

    <!-- Modal -->
    <div class="modal fade" id="md" tabindex="-1" aria-labelledby="mdlb" aria-hidden="true">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="mdlb">Product</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <table class="table table-primary table-bordered text-center">
                        <thead>
                        <tr>
                            <td>Name</td>
                            <td>Image</td>
                            <td>Price</td>
                            <td>Quantity</td>
                            <td colspan="2">Manipulation</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${ data.content}" var="pro">
                            <tr>
                                <td>${pro.name}</td>
                                <td><img src="${pro.image}" height="75"></td>
                                <td class="fw-bold text-danger"><fmt:formatNumber type="number" pattern="##,###VNĐ" value="${pro.price}"/></td>
                                <form:form action="/admin/order/${order.id}/add/${pro.id}" method="get">
                                    <td><input type="number" class="form-control" name="quantity"/></td>
                                    <td><button type="submit" class="btn btn-primary">Add to order</button></td>
                                </form:form>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
