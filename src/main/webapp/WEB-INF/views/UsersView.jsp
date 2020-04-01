<%@ page import="ru.job4j.servlets.model.User" %>
<%@ page import="ru.job4j.servlets.repository.Dispatcher" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>UsersJSP</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/" method="post">
    Name : <input type="text" name="name"></br>
    Login : <input type="text" name="login"></br>
    Email : <input type="text" name="email"></br>
    PhotoId :
    <form action="${pageContext.servletContext.contextPath}/upload" method="post" enctype="multipart/form-data">
        <div class="checkbox">
            <input type="file" name="photoId">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>



    <table style="border: black;" cellpadding="1" cellspacing="1" border="1">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Login</th>
            <th>Email</th>
            <th>Date</th>
            <th>PhotoId</th>
        </tr>
        <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"></c:out></td>
            <td><c:out value="${user.name}"></c:out></td>
            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.email}"></c:out></td>
            <td><c:out value="${user.createDate}"></c:out></td>
            <td>
                <img src="${pageContext.servletContext.contextPath}/download?name=${user.photoId}" width="30px" height="30px"/>
                <br>
                <c:out value="${user.photoId}"></c:out>
                <br>
                <a href="${pageContext.servletContext.contextPath}/download?name=${user.photoId}">Download</a>
           </td>
        </tr>
        </c:forEach>
    </table>
</form>
</body>
</html>
