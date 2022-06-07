<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
    <c:if test="${!empty sessionScope.error }">
        <div class="alert alert-danger mt-3">
                ${ sessionScope.error }
            <c:remove var="error" scope="session"/>
        </div>
    </c:if>
    <h2 class="text-center mt-3 fw-bold">CART</h2>
    <a class="btn btn-primary offset-11 mb-4"
       href="/SANGTM_PH17730_ASM/home/card/clear">Clear cart</a>
    <table class="table text-center">
        <thead class="">
        <tr>
            <th></th>
            <th>Product</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Total</th>
            <th>Manipulation</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sessionScope.orderDetails }" var="oDetail">
            <tr>
                <td><img height="100" alt="" src="${oDetail.product.image }">
                </td>
                <td>${ oDetail.product.name }</td>
                <td><fmt:formatNumber type="number" pattern="##,###₫"
                                      value="${oDetail.price}"/></td>
                <td>${oDetail.quantity }</td>
                <td><fmt:formatNumber type="number" pattern="##,###₫"
                                      value="${oDetail.price * oDetail.quantity}"/></td>
                <td>
                    <btn class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal${oDetail.id}">Edit
                        quantity
                    </btn>
                </td>

                <!-- Modal -->
                <div class="modal fade" id="exampleModal${oDetail.id}" tabindex="-1"
                     aria-labelledby="exampleModalLabel${oDetail.id}" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                                <%--@elvariable id="od" type="lombok"--%>
                            <form:form modelAttribute="od">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel${oDetail.id}">Edit quantity</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">

                                    <form:label path="quantity" cssClass="form-lable">Quantity:</form:label>
                                    <form:input type="number" path="quantity" value="${oDetail.quantity}" cssClass="form-control" min="1" max="99"/>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close
                                    </button>
                                    <a type="submit" class="btn btn-primary" href="/home/guest/order/update/quantity/${oDetail.id}">Update</a>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>

                <td>
                    <btn class="btn btn-danger">Delete</btn>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <hr>
    <div class="row">
        <%--@elvariable id="order" type="lombok"--%>
        <form:form class="row mt-2" action="/home/guest/cart/order" modelAttribute="order" method="post">
            <div class="col-5">
                <h3>Customer</h3>
                <div class="mb-3">
                    <form:label path="fullname" for="fullname" class="form-label">Fullname</form:label>
                    <form:input path="fullname" class="form-control" id="fullname" name="hoTen"/>
                </div>
                <div class="mb-3">
                    <form:label path="phone" for="sdt" class="form-label">Phone</form:label>
                    <form:input path="phone" class="form-control" id="sdt" name="sdt"/>
                </div>
                <div class="mb-3">
                    <form:label path="address" for="dc" class="form-label">Address:</form:label>
                    <form:input path="address" class="form-control" id="dc" name="diaChi"/>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3 offset-4">
                    <h4 class="col-auto">
                        Total payment: <span class="text-danger fw-bold"><fmt:formatNumber
                            type="number" pattern="##,###₫" value="${total}"/></span>
                    </h4>
                    <button type="button" class="btn btn-primary"
                            data-bs-toggle="modal" data-bs-target="#Modal">Order
                    </button>
                    <div class="modal fade" id="Modal" tabindex="-1"
                         aria-labelledby="exampleModalLabel1" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel1">Message</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">Order confirmation?</div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary"
                                            data-bs-dismiss="modal">Cancel
                                    </button>
                                    <button type="submit" class="btn btn-primary">Confirm</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>