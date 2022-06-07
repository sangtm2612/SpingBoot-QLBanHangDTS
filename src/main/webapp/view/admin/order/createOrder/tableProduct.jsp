<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="i" scope="session" value="0"/>

<div>
    <%--@elvariable id="o" type="lombok"--%>
    <form:form modelAttribute="o" action="/admin/order/add/${pro.id}" method="post">
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
                    <c:set var="i" scope="session" value="${i = i + 1}"/>
                    <td>${pro.name}</td>
                    <td><img src="${pro.image}" height="75"></td>
                    <td class="fw-bold text-danger"><fmt:formatNumber type="number" pattern="##,###VNĐ" value="${pro.price}"/></td>
                    <form action="/admin/order/add/${pro.id}" method="get">
                    <td><input type="number" class="form-control" name="quantity"/></td>
                        <td><button type="submit" class="btn btn-primary">Add to order</button></td>
                    </form>
<%--                    <td><form:input type="number" path="quantitys[${i}]" ></form:input></td>--%>
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

    </form:form>
</div>
