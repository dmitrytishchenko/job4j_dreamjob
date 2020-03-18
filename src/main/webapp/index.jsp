<%@ page import="ru.job4j.servlets.model.User" %>
<%@ page import="ru.job4j.servlets.repository.Dispatcher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UsersJSP</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/create" method="post">
    Name :  <input type="text" name="name"></br>
    Login : <input type="text" name="login"></br>
    Email : <input type="text" name="email"></br>
    <input type="submit">

    <table style="border: black;" cellpadding="1" cellspacing="1" border="1">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Login</th>
            <th>Email</th>
            <th>Date</th>
        </tr>
        <%for (User user : Dispatcher.getDispatcher().findAll()) {%>
        <tr>
            <td><%=user.getId()%>
            </td>
            <td><%=user.getName()%>
            </td>
            <td><%=user.getLogin()%>
            </td>
            <td><%=user.getEmail()%>
            </td>
            <td><%=user.getCreateDate()%>
            </td>
                <%}%>
    </table>
</form>
</body>
</html>
