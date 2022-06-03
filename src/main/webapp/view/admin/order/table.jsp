<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<div>
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
            <table class="table table-primary table-bordered text-center">
                <thead>
                    <tr>
                        <th>Id</th>
                        <td>CreateDate</td>
                        <td>Staff</td>
                        <td>Customer</td>
                        <td>Phone</td>
                        <td>Address</td>
                        <td>Total</td>
                        <td>Status</td>
                        <td colspan="2">Manipulation</td>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${ data.content}" var="order">
                    <tr>
                        <td>${order.id}</td>
                        <td><fmt:formatDate pattern ="hh:mm:ss dd/MM/yyyy" value ="${order.createDate}"/></td>
                        <td>${order.account.username}</td>
                        <td>${order.fullname}</td>
                        <td>${order.phone}</td>
                        <td>${order.address}</td>
                        <td class="fw-bold text-success"><fmt:formatNumber type="number" pattern="##,###VNĐ" value="${order.total}"/></td>
                        <td class="fw-bold ${order.status == 0 ? 'text-secondary' : order.status == 1 ? 'text-warning' : 'text-success'}">${order.status == 0 ? 'Prepare' : order.status == 1 ? 'Transport' : 'Complete'}</td>
                        <td><a class="btn btn-primary" href="/admin/order/view/${order.id}">View</a></td>
                        <td><a class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal${order.id}">Delete</a></td>

                        <!-- Modal delete-->
                        <div class="modal fade" id="exampleModal${order.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Thông báo</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        Bạn có muốn xóa hóa đơn ${order.id}?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                        <a class="btn btn-danger" href="/admin/order/delete/${order.id}">Xóa</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </tr>
                </c:forEach>
                </tbody>

            </table>

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
        <div class="col-6"></div>
    </div>
</div>
