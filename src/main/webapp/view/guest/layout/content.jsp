<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="mt-3">
    <c:if test="${!empty sessionScope.error }">
        <div class="alert alert-danger">
                ${ sessionScope.error }
            <c:remove var="error" scope="session"/>
        </div>
    </c:if>
    <c:if test="${!empty sessionScope.message }">
        <div class="alert alert-success">
                ${ sessionScope.message }
            <c:remove var="message" scope="session"/>
        </div>
    </c:if>
    <hr class="bg-black" style="height: 3px">
    <div class="row ">
        <c:forEach items="${data.content }" var="product">
            <div class="card col-2 m-3 text-center">
                <img src="${product.image }"
                     class="card-img-top mt-2" alt="...">
                <div class="card-body">
                    <h5 class="card-title">${ product.name }</h5>
                    <p class="card-text text-danger fw-bold"><fmt:formatNumber type="number" pattern="##,###VNĐ" value="${product.price}" /></p>
                    <%--@elvariable id="orderDetail" type="lombok"--%>
                    <form:form action="/home/guest/cart/product/${product.id}" modelAttribute="orderDetail" method="post">
                        <div class="col-12 mb-3">
                            <form:input type="number" path="quantity" name="quantity" placeholder="Quantity" min="1" max="99" class="form-control form-control-sm"/>
                        </div>
                        <button type="submit" class="btn btn-primary">Add to cart</button>
                    </form:form>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="row">
        <div class="col-auto w-100">
            <nav aria-label="Page navigation example w-100">
                <ul class="pagination w-100 d-flex justify-content-center">
                    <li class="page-item ${data.number == 0 ? 'disabled' :''}"><a class="page-link" href="/home/guest/index?page=0">First page</a></li>
                    <li class="page-item ${data.number == 0 ? 'disabled' :''}"><a class="page-link" href="/home/guest/index?page=${ data.number - 1 }" >Previous page</a></li>
                    <li class="page-item"><a class="page-link"> ${data.number + 1}/${data.totalPages}</a></li>
                    <li class="page-item ${data.number == data.totalPages - 1 ? 'disabled' :''}"><a class="page-link" href="/home/guest/index?page=${ data.number + 1 }">Next page</a></li>
                    <li class="page-item ${data.number == data.totalPages - 1 ? 'disabled' :''}"><a class="page-link" href="/home/guest/index?page=${ data.totalPages - 1 }">Last page</a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>
