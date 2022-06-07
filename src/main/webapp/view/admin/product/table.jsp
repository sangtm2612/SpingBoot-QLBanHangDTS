<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<div>
            <table class="table text-center">
                <thead>
                    <tr class="table table-dark">
                        <th>Id</th>
                        <td>Name</td>
                        <td>Image</td>
                        <td>Price</td>
                        <td>Create Date</td>
                        <td>Avariable</td>
                        <td colspan="2">Manipulation</td>
                    </tr>
                </thead>
                <c:forEach items="${ data.content}" var="pro">
                    <tr>
                        <td>${pro.id}</td>
                        <td>${pro.name}</td>
                        <td><img src="${pro.image}" height="75"></td>
                        <td class="fw-bold text-danger"><fmt:formatNumber type="number" pattern="##,###VNĐ" value="${pro.price}"/></td>
                        <td><fmt:formatDate pattern ="hh:mm:ss dd/MM/yyyy" value ="${pro.createdDate}" /></td>
                        <td>${pro.available == 1 ? 'Yes' : 'No'}</td>
                        <td><a class="btn btn-primary" href="/admin/product/edit/${pro.id}">Edit</a></td>
                        <td><a class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal${pro.id}">Delete</a></td>

                        <!-- Modal -->
                        <div class="modal fade" id="exampleModal${pro.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Thông báo</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        Bạn có muốn xóa sản phẩm ${pro.id}?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                        <a class="btn btn-danger" href="/admin/product/delete/${pro.id}">Xóa</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </tr>
                </c:forEach>

            </table>

    <div class="row">
        <div class="col-auto w-100">
            <nav aria-label="Page navigation example w-100">
                <ul class="pagination w-100 d-flex justify-content-center">
                    <li class="page-item ${data.number == 0 ? 'disabled' :''}"><a class="page-link" href="/admin/category/index?page=0">First page</a></li>
                    <li class="page-item ${data.number == 0 ? 'disabled' :''}"><a class="page-link" href="/admin/category/index?page=${ data.number - 1 }" >Previous page</a></li>
                    <li class="page-item"><a class="page-link"> ${data.number + 1}/${data.totalPages}</a></li>
                    <li class="page-item ${data.number == data.totalPages - 1 ? 'disabled' :''}"><a class="page-link" href="/admin/category/index?page=${ data.number + 1 }">Next page</a></li>
                    <li class="page-item ${data.number == data.totalPages - 1 ? 'disabled' :''}"><a class="page-link" href="/admin/category/index?page=${ data.totalPages - 1 }">Last page</a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>
