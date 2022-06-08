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
    <c:set var="i" scope="session" value="0"/>
            <table class="table text-center">
                <thead>
                    <tr class="table-dark">
                        <th></th>
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
                        <td><input type="checkbox" value="${i}"> </td>
                        <c:set var="i" scope="session" value="${i = i + 1}"/>
                        <td>${order.id}</td>
                        <td><fmt:formatDate pattern ="hh:mm:ss dd/MM/yyyy" value ="${order.createDate}"/></td>
                        <td>${order.account.username}</td>
                        <td>${order.fullname}</td>
                        <td>${order.phone}</td>
                        <td>${order.address}</td>
                        <td class="fw-bold text-danger"><fmt:formatNumber type="number" pattern="##,###VNĐ" value="${order.total}"/></td>
                        <td class="btn-group btn-group-sm" role="group" aria-label="Basic outlined example">
                            <a class="btn btn-outline-secondary ${order.status == 0 ? 'active' : 'disabled'}">Wait</a>
                            <a class="btn btn-outline-primary ${order.status == 1 ? 'active' : order.status == 2 || order.status == 3 ? 'disabled' : ''}" href="/admin/order/status/update/1/${order.id}">Prepare</a>
                            <a class="btn btn-outline-warning ${order.status == 2 ? 'active' : order.status == 0 || order.status == 3 ? 'disabled' : ''}" href="/admin/order/status/update/2/${order.id}">Transport</a>
                            <a class="btn btn-outline-success ${order.status == 3 ? 'active' : order.status == 0 || order.status == 1 ? 'disabled' : ''}" href="/admin/order/status/update/3/${order.id}">Complete</a>
                        </td>
                        <td><a class="btn btn-primary" href="/admin/order/view/${order.id}">View</a></td>
                        <td><a class="btn btn-primary ${order.status == 2 || order.status == 3 ? 'disabled' : ''}" href="/admin/order/edit/${order.id}">Edit</a></td>
                        <td><a class="btn btn-danger ${order.status == 2 || order.status == 3 ? 'disabled' : ''}" data-bs-toggle="modal" data-bs-target="#exampleModal${order.id}">Delete</a></td>




                        <!-- Modal delete-->
                        <div class="modal fade" id="exampleModal${order.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Message</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        Do you want to delete order ${order.id}?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                        <a class="btn btn-danger" href="/admin/order/delete/${order.id}">Confirm</a>
                                    </div>
                                </div>
                            </div>
                        </div>

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
