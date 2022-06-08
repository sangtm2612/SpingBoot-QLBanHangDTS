<%--
  Created by IntelliJ IDEA.
  User: TranSang
  Date: 6/8/2022
  Time: 9:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up Form by Colorlib</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h1>Sign up</h1>
<div class="container">
    <div class="sign-up-content">
        <form method="POST" class="signup-form">
            <h2 class="form-title">Register</h2>
            <div class="form-textbox">
                <label for="name">Full name</label>
                <input type="text" name="name" id="name" style="padding: 16px 30px 16px 165px;"/>
            </div>

            <div class="form-textbox">
                <label for="email">Email</label>
                <input type="email" name="email" id="email" style="padding: 16px 30px 16px 165px;"/>
            </div>

            <div class="form-textbox">
                <label for="pass">Password</label>
                <input type="password" name="pass" id="pass" style="padding: 16px 30px 16px 165px;"/>
            </div>
            <div class="form-textbox">
                <label for="pass">Password confirm</label>
                <input type="password" name="pass" id="pass" style="padding: 16px 30px 16px 165px;"/>
            </div>
            <div class="form-textbox">
                <label for="pass">Phone</label>
                <input type="text" name="pass" id="pass" style="padding: 16px 30px 16px 165px;"/>
            </div>
            <div class="form-textbox">
                <input type="submit" name="submit" id="submit" class="submit" value="Create account" />
            </div>
        </form>
        <p class="loginhere">
            Already have an account ?<a href="/login" class="loginhere-link"> Log in</a>
        </p>
    </div>
</div>
</body>
</html>
