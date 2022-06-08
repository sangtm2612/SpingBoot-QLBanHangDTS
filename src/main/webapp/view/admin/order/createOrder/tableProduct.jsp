<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:if test="${!empty sessionScope.message}">
    <div class="alert alert-success mt-3" role="alert">
            ${sessionScope.message}
    </div>
    <c:remove var="message" scope="session"></c:remove>
</c:if>
<div>
    <h3 class="text-center">Table product</h3>
    <table class="table text-center">
        <thead>
        <tr class="">
            <th></th>
            <th>Name</th>
            <th>Image</th>
            <th>Price</th>
            <th>Quantity</th>
            <th colspan="2">Manipulation</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${ data.content}" var="pro">
                <tr>
                    <td><input type="checkbox" value="${pro.id}"></td>
                    <td>${pro.name}</td>
                    <td><img src="${pro.image}" height="75"></td>
                    <td class="fw-bold text-danger"><fmt:formatNumber type="number" pattern="##,###VNĐ" value="${pro.price}"/></td>
                    <form action="/admin/order/add/${pro.id}" method="get">
                    <td><input type="number" class="form-control" name="quantity"/></td>
                        <td><button type="submit" class="btn btn-primary">Add to order</button></td>
                    </form>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:if test="${!empty sessionScope.message}">
        <div class="alert alert-success mt-3" role="alert">
            Success!
        </div>
        <c:remove var="message" scope="session"></c:remove>
    </c:if>
</div>
