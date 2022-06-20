<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
        <div>
            <h1>DiscountManagement</h1>
            <%--@elvariable id="dis" type="lombok"--%>
            <form:form action="/admin/category/store" method="post" modelAttribute="dis">
                <div class="mt-3">
                    <form:label path="code" class="form-lable">Code:</form:label>
                    <form:input path="code" name="name" class="form-control mb-1"/>
                </div>
                <div class="mt-3">
                    <form:label path="minOrderValue" class="form-lable">Min order value:</form:label>
                    <form:input path="minOrderValue" name="name" class="form-control mb-1"/>
                </div>
                <div class="mt-3">
                    <form:label path="value" class="form-lable">Discount value:</form:label>
                    <form:input path="value" name="name" class="form-control mb-1"/>
                </div>
                <div class="mt-3">
                    <form:label path="maxDiscount" class="form-lable">Max discount value:</form:label>
                    <form:input path="maxDiscount" name="name" class="form-control mb-1"/>
                </div>
                <div class="mt-3">
                    <form:label path="unit" class="form-lable">Unit:</form:label>
                    <form:radiobutton path="unit" value="1" label="$" checked="checked"/>
                    <form:radiobutton path="unit" value="0" label="%"/>
                </div>
                <div class="mt-3">
                    <form:label path="available" class="form-lable">Available:</form:label>
                    <form:radiobutton path="available" value="1" label="Yes" checked="checked"/>
                    <form:radiobutton path="available" value="0" label="No"/>
                </div>
                <form:button type="submit" class="btn btn-primary">Insert</form:button>
            </form:form>
        </div>
