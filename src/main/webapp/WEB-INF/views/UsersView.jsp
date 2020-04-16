<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>UsersJSP</title>
</head>
<br>
<h2>Create a new user</h2>
<form action="${pageContext.servletContext.contextPath}/" method="post" enctype="multipart/form-data">
    Name : <input type="text" name="name"></br>
    Login : <input type="text" name="login"></br>
    Password : <input type="password" name="password"></br>
    Email : <input type="text" name="email"></br>
    Role : <select name="role">
    <option value="admin">Admin</option>
    <option value="user">User</option>
    <option value="guest">Guest</option>
</select></br>
    PhotoId :
    <div class="checkbox">
        <input type="file" name="photoId">
    </div>
    <button type="submit" class="btn btn-default">Submit</button>

    <h2>Show the all users!</h2>
    <table style="border: black;" cellpadding="1" cellspacing="1" border="1">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Login</th>
            <th>Email</th>
            <th>Role</th>
            <th>Date</th>
            <th>PhotoId</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"></c:out></td>
                <td><c:out value="${user.name}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.role}"></c:out></td>
                <td><c:out value="${user.createDate}"></c:out></td>
                <td>
                    <img src="${pageContext.servletContext.contextPath}/download?name=${user.photoId}" width="30px"
                         height="30px"/>
                    <br>
                    <c:out value="${user.photoId}"></c:out>
                    <br>
                    <a href="${pageContext.servletContext.contextPath}/download?name=${user.photoId}">Download</a>
                </td>
                <td><a href="${pageContext.servletContext.contextPath}/edit?name=${user.id}">Edit</a></td>
                <td><a href="${pageContext.servletContext.contextPath}/delete?name=${user.id}">Delete</a></td>

            </tr>
        </c:forEach>
    </table>
</form>
</br>
<a href="${pageContext.servletContext.contextPath}/exit">Exit</a>
</body>
</html>
