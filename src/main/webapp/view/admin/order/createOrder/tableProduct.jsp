<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<div>
    <h3 class="text-center">Table product</h3>
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


<%--    <div class="row">--%>
<%--        <div class="col-6">--%>
<%--            <ul class="pagination">--%>
<%--                <li class="page-item">--%>
<%--                    <a href="/admin/product/index">--%>
<%--                        First page--%>
<%--                    </a>--%>
<%--                </li>--%>
<%--                <li class="page-item">--%>
<%--                    <a href="/admin/product/index?page=${ data.number - 1 }">--%>
<%--                        Previous page--%>
<%--                    </a>--%>
<%--                </li>--%>
<%--                <li class="page-item">--%>
<%--                    <a href="#">--%>
<%--                        ${ data.number }--%>
<%--                    </a>--%>
<%--                </li>--%>
<%--                <li class="page-item">--%>
<%--                    <a href="/admin/product/index?page=${ data.number + 1 }">--%>
<%--                        Next page--%>
<%--                    </a>--%>
<%--                </li>--%>
<%--                <li class="page-item">--%>
<%--                    <a href="/admin/product/index?page=${ data.totalPages - 1 }">--%>
<%--                        Last page--%>
<%--                    </a>--%>
<%--                </li>--%>
<%--            </ul>--%>
<%--        </div>--%>
<%--        <div class="col-6"></div>--%>
<%--    </div>--%>
</div>
