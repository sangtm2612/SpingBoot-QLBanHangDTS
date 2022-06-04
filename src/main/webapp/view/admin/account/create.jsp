<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
        <div>
            <h1>AccountManagement</h1>
            <%--@elvariable id="acc" type="lombok"--%>
            <form:form action="/admin/account/store" method="post" enctype="multipart/form-data" modelAttribute="acc">
                <div class="mt-3">
                    <form:label path="username" class="form-lable">Username:</form:label>
                    <form:input path="username" name="username" class="form-control"/>
                </div>
                <div class="mt-3">
                    <form:label path="password" class="form-lable">Password:</form:label>
                    <form:input type="password" path="password" name="password" class="form-control"/>
                </div>
                <div class="mt-3">
                    <form:label path="fullname" class="form-lable">Fullname:</form:label>
                    <form:input path="fullname" name="fullname" class="form-control"/>
                </div>
                <div class="mt-3">
                    <form:label path="email" class="form-lable">Email:</form:label>
                    <form:input type="email" path="email" name="email" class="form-control"/>
                </div>
                <div class="mt-3">
                    <form:label path="" class="form-lable">Photo:</form:label>
                    <form:input type="file" path="" name="attach" class="form-control"/>
                </div>
                <div class="mt-3">
                    <form:label path="admin" class="form-lable">Role:</form:label>
                    <form:radiobutton path="admin" value="1" label="Admin" checked="checked"/>
                    <form:radiobutton path="admin" value="0" label="User"/>
                </div>
                <div class="mt-3">
                    <form:label path="activated" class="form-lable">Activated:</form:label>
                    <form:radiobutton path="activated" value="1" label="Yes" checked="checked"/>
                    <form:radiobutton path="activated" value="0" label="No"/>
                </div>
                <button type="submit" class="btn btn-primary mt-3">Insert</button>
            </form:form>
        </div>
