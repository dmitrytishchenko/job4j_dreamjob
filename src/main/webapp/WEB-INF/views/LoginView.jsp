<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 10.04.2020
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login View</title>
</head>
<body>
<h3>Insert login and password</h3>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/signin" method="post">
    Login : <input type="text" name="login"></br>
    Password : <input type="password" name="password"></br>
    <button type="submit" class="btn btn-default">Submit</button>
</form>
</body>
</html>
