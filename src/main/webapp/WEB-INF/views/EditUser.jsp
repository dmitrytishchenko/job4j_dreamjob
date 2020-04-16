<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 13.04.2020
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<h3>This is a old user</h3>
<table style="border: black;" cellpadding="1" cellspacing="1" border="1">
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
</table>

<h2>Edit the user</h2>
<form action="${pageContext.servletContext.contextPath}/edit" method="post">
    Id : <input type="text" name="id"></br>
    Name : <input type="text" name="name"></br>
    Login : <input type="text" name="login"></br>
    Email : <input type="text" name="email"></br>
    Role : <select name="role">
        <option value="admin">Admin</option>
        <option value="user">User</option>
        <option value="guest">Guest</option>
    </select></br>
    <button type="submit" class="btn btn-default">Submit</button>
</form>
</body>
</html>
