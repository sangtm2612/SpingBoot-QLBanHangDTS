<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div>
            <table class="table table-primary table-bordered text-center">
                <thead>
                    <tr>
                        <th>Id</th>
                        <td>Username</td>
                        <td>Fullname</td>
                        <td>Email</td>
                        <td>Photo</td>
                        <td>Role</td>
                        <td>Activated</td>
                        <td colspan="2">Manipulation</td>
                    </tr>
                </thead>
                <c:forEach items="${ data.content}" var="acc">
                    <tr>
                        <td>${acc.id}</td>
                        <td>${acc.username}</td>
                        <td>${acc.fullname}</td>
                        <td>${acc.email}</td>
                        <td><img src="${acc.photo}" width="75"></td>
                        <td>${acc.admin == 1 ? "Admin" : "User"}</td>
                        <td class="fw-bold ${acc.activated == 1 ? "text-success" : "text-danger"}">${acc.activated == 1 ? "Yes" : "No"}</td>
                        <td><a class="btn btn-primary" href="/admin/account/edit/${acc.id}">Edit</a></td>
                        <td><a class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal${acc.id}">Delete</a></td>

                        <!-- Modal -->
                        <div class="modal fade" id="exampleModal${acc.id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Thông báo</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        Bạn có muốn xóa tài khoản ${acc.id}?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                        <a class="btn btn-danger" href="/admin/account/delete/${acc.id}">Xóa</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </tr>
                </c:forEach>

            </table>

    <div class="row">
        <div class="col-6">
            <ul class="pagination">
                <li class="page-item">
                    <a href="/admin/account/index">
                        First page
                    </a>
                </li>
                <li class="page-item">
                    <a href="/admin/account/index?page=${ data.number - 1 }">
                        Previous page
                    </a>
                </li>
                <li class="page-item">
                    <a href="#">
                        ${ data.number }
                    </a>
                </li>
                <li class="page-item">
                    <a href="/admin/account/index?page=${ data.number + 1 }">
                        Next page
                    </a>
                </li>
                <li class="page-item">
                    <a href="/admin/account/index?page=${ data.totalPages - 1 }">
                        Last page
                    </a>
                </li>
            </ul>
        </div>
        <div class="col-6"></div>
    </div>
</div>
