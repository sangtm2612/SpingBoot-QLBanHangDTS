<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
        <div>
            <h1>CategoryManagement</h1>
            <%--@elvariable id="cate" type="lombok"--%>
            <form:form action="/admin/category/update/${cate.id}" method="post" modelAttribute="cate">
                <form:label path="name" class="form-lable">Name:</form:label>
                <form:input path="name" name="name" class="form-control" value="${cate.name }"/>
<%--                <button type="submit" class="btn btn-primary mt-3">Insert</button>--%>
                <button type="submit" class="btn btn-primary mt-3">Update</button>
            </form:form>
        </div>

