<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
            <h1>ProductManagement</h1>
            <%--@elvariable id="pro" type="lombok"--%>
            <form:form action="/admin/product/store" method="post" modelAttribute="pro" enctype="multipart/form-data">
                <form:select cssClass="form-select" path="categoryId">
                    <form:option value="None">--Category--</form:option>
                    <form:options items="${listCate}" itemValue="id" itemLabel="name" ></form:options>
                </form:select>
                <div class="mt-3">
                    <form:label path="name" class="form-lable">Product:</form:label>
                    <form:input path="name" name="name" class="form-control"/>
                    <div><form:errors cssClass="text-danger" path="name"></form:errors> </div>
                </div>
                <div class="mt-3">
                    <form:label path="" class="form-lable">Image:</form:label>
                    <form:input type="file" path="" name="attach" class="form-control"/>
                </div>
                <div class="mt-3">
                    <form:label path="price" class="form-lable">Price:</form:label>
                    <form:input type="number" path="price" name="price" class="form-control"/>
                </div>
                <div class="mt-3">
                    <form:label path="available" class="form-lable">Available:</form:label>
                    <form:radiobutton path="available" value="1" label="Yes" checked="checked"/>
                    <form:radiobutton path="available" value="0" label="No"/>
                </div>
                <button type="submit" class="btn btn-primary mt-3">Insert</button>
                <a class="btn btn-primary mt-3" data-bs-toggle="modal" data-bs-target="#exampleModal">Import</a>
                <a class="btn btn-primary mt-3" href="/admin/product/export">Export</a>
            </form:form>

            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <form action="/admin/product/import" method="post" enctype="multipart/form-data">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Insert Products</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                                <div class="">
                                    <label class="form-lable">File excel:</label>
                                    <input type="file" name="excel" class="form-control"/>
                                </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Upload</button>
                        </div>
                    </div>
                </div>
                </form>
            </div>

        </div>
