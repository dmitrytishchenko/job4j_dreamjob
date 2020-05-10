<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 16.04.2020
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Guest Role View</title>
</head>
<body>
<h2>Show the all users!</h2>
<table style="border: black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>Country</th>
        <th>City</th>
        <th>Role</th>
        <th>Date</th>
        <th>PhotoId</th>
    </tr>
    <tr>
        <td><c:out value="${requestScope.user.id}"></c:out></td>
        <td><c:out value="${requestScope.user.name}"></c:out></td>
        <td><c:out value="${requestScope.user.login}"></c:out></td>
        <td><c:out value="${requestScope.user.email}"></c:out></td>
        <td><c:out value="${requestScope.user.country}"></c:out></td>
        <td><c:out value="${requestScope.user.city}"></c:out></td>
        <td><c:out value="${requestScope.user.role}"></c:out></td>
        <td><c:out value="${requestScope.user.createDate}"></c:out></td>
        <td>
            <img src="${pageContext.servletContext.contextPath}/download?name=${user.photoId}" width="30px"
                 height="30px"/>
            <br>
            <c:out value="${user.photoId}"></c:out>
            <br>
        </td>
    </tr>
</table>
<a href="${pageContext.servletContext.contextPath}/exit">Exit</a>
</body>
</html>
