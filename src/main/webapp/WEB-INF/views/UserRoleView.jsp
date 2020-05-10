<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 16.04.2020
  Time: 9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Role View</title>
</head>
<br>
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
            <c:out value="${requestScope.user.photoId}"></c:out>
            <br>
            <a href="${pageContext.servletContext.contextPath}/download?name=${user.photoId}">Download</a>
        </td>
    </tr>
</table>
<h2>Edit this user</h2>
<form action="${pageContext.servletContext.contextPath}/edit" method="post">
    Id : <input type="text" name="id"></br>
    Name : <input type="text" name="name"></br>
    Login : <input type="text" name="login"></br>
    Email : <input type="text" name="email"></br>
    <button type="submit" class="btn btn-default">Submit</button>
</form>
</br>
<a href="${pageContext.servletContext.contextPath}/exit">Exit</a>
</body>
</html>
