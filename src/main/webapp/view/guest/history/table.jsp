<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<div class="mt-3">
    <c:if test="${!empty sessionScope.error}">
        <div class="alert alert-danger mt-3" role="alert">
            ${sessionScope.error}
        </div>
        <c:remove var="error" scope="session"></c:remove>
    </c:if>
    <c:if test="${!empty sessionScope.message}">
        <div class="alert alert-success mt-3" role="alert">
                ${sessionScope.message}
        </div>
        <c:remove var="message" scope="session"></c:remove>
    </c:if>

            <table class="table text-center">
                <thead>
                    <tr class="table-dark">
                        <th>Id</th>
                        <th>CreateDate</th>
                        <th>Staff</th>
                        <th>Customer</th>
                        <th>Phone</th>
                        <th>Address</th>
                        <th>Total</th>
                        <th>Status</th>
                        <th colspan="3">Manipulation</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${ data.content}" var="order">
                    <tr>
                        <c:set var="i" scope="session" value="${i = i + 1}"/>
                        <td>${order.id}</td>
                        <td><fmt:formatDate pattern ="hh:mm:ss dd/MM/yyyy" value ="${order.createDate}"/></td>
                        <td>${order.account.username}</td>
                        <td>${order.fullname}</td>
                        <td>${order.phone}</td>
                        <td>${order.address}</td>
                        <td class="fw-bold text-danger"><fmt:formatNumber type="number" pattern="##,###VNĐ" value="${order.total}"/></td>
                        <td class="fw-bold ${order.status == 0 ? 'text-secondary' : order.status == 1 ? 'text-primary' : order.status == 2 ? 'text-warning' : 'text-success'}">${order.status == 0 ? 'Wait' : order.status == 1 ? 'Prepare' : order.status == 2 ? 'Transport' : 'Complete'}</td>

                        <td><a class="btn btn-primary" href="/home/guest/order/view/${order.id}">View</a></td>

                    </tr>
                </c:forEach>
                </tbody>

            </table>

    <div class="row">
        <div class="col-auto w-100">
            <nav aria-label="Page navigation example w-100">
                <ul class="pagination w-100 d-flex justify-content-center">
                    <li class="page-item ${data.number == 0 ? 'disabled' :''}"><a class="page-link" href="/admin/order/index?page=0">First page</a></li>
                    <li class="page-item ${data.number == 0 ? 'disabled' :''}"><a class="page-link" href="/admin/order/index?page=${ data.number - 1 }" >Previous page</a></li>
                    <li class="page-item"><a class="page-link"> ${data.number + 1}/${data.totalPages}</a></li>
                    <li class="page-item ${data.number == data.totalPages - 1 ? 'disabled' :''}"><a class="page-link" href="/admin/order/index?page=${ data.number + 1 }">Next page</a></li>
                    <li class="page-item ${data.number == data.totalPages - 1 ? 'disabled' :''}"><a class="page-link" href="/admin/order/index?page=${ data.totalPages - 1 }">Last page</a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>
