<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 17.04.2020
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<h2>Create a new user</h2>
<form action="${pageContext.servletContext.contextPath}/create" method="post">
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
</form>
</body>
</html>
