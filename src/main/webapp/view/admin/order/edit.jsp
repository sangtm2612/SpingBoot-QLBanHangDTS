<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div>
    <h1>ProductManagement</h1>
    <%--@elvariable id="pro" type="lombok"--%>
    <form:form action="/admin/product/update/${pro.id}" method="post" modelAttribute="pro">
<%--        <form:select cssClass="form-select" path="categoryId">--%>
<%--            <form:option value="${pro.category.id}">${pro.category.name}</form:option>--%>
<%--            <form:options items="${listCate}" itemValue="id" itemLabel="name"></form:options>--%>
<%--        </form:select>--%>
        <div class="mt-3">
            <form:label path="name" class="form-lable">Product:</form:label>
            <form:input path="name" name="name" class="form-control"/>
        </div>
        <div class="mt-3">
            <form:label path="" class="form-lable">Image:</form:label>
            <form:input type="file" path="" name="image" class="form-control"/>
        </div>
        <div class="mt-3">
            <form:label path="price" class="form-lable">Price:</form:label>
            <form:input type="number" path="price" name="price" class="form-control"/>
        </div>
        <div class="mt-3">
            <form:label path="available" class="form-lable">Available:</form:label>
            <form:radiobutton path="available" value="1" label="1" checked="checked"/>
            <form:radiobutton path="available" value="0" label="0"/>
        </div>
        <button type="submit" class="btn btn-primary mt-3">Update</button>
    </form:form>
</div>
