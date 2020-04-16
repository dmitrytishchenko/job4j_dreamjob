<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 15.04.2020
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delete User</title>
</head>
<body>
<h3>This is a user that need deleted</h3>
<table style="border: red" cellpadding="1" , cellspacing="1" , border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>Role</th>
        <th>Date</th>
        <th>PhotoId</th>
    </tr>
    <tr>
        <td><c:out value="${requestScope.user.id}"></c:out></td>
        <td><c:out value="${requestScope.user.name}"></c:out></td>
        <td><c:out value="${requestScope.user.login}"></c:out></td>
        <td><c:out value="${requestScope.user.email}"></c:out></td>
        <td><c:out value="${requestScope.user.role}"></c:out></td>
        <td><c:out value="${requestScope.user.createDate}"></c:out></td>
        <td>
            <img src="${pageContext.servletContext.contextPath}/download?name=${user.photoId}" width="30px"
                 height="30px"/>
            <br>
            <c:out value="${requestScope.user.photoId}"></c:out>
            <br>
            <a href="${pageContext.servletContext.contextPath}/download?name=${user.photoId}">Download</a>
        </td>
    </tr>
    </br>
</table>
<h3>Delete this User</h3>

<form action="${pageContext.servletContext.contextPath}/delete" method="post">
    Id : <input type="text" name="id"></br>
    <button type="submit" class="btn btn-default">Delete</button>
</form>
</body>
</html>
