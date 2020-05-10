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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script>
        function validate() {
            var result = true;
            var name = $('#name').val();
            if (name == '') {
                alert($('#name').attr('placeholder'));
                result = false;
            }
            var login = $('#login').val();
            if (login == '') {
                alert($('#login').attr('placeholder'));
                result = false;
            }
            var password = $('#password').val();
            if (password == '') {
                alert($('#password').attr('placeholder'));
                result = false;
            }
            var email = $('#email').val();
            if (email == '') {
                alert($('#email').attr('placeholder'));
                result = false;
            }
            var country = $('#country').val();
            if (country == 'Select country') {
                alert($('#country').attr('placeholder'));
                result = false;
            }
            var city = $('#city').val();
            if (city == 'Select city') {
                alert($('#city').attr('placeholder'));
                result = false;
            }
            var role = $('#role').val();
            if (role == 'Select role') {
                alert('Enter role!!!');
                result = false;
            }
            var photoId = $('#photoId').val();
            if (photoId == '') {
                alert('Upload foto!');
                result = false;
            }
            return result;
        }
    </script>
    <script>
        function changeCountry(idCountry) {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/items/city',
                data: 'name=' + idCountry,
                datatype: 'text'
            }).done(function (data) {
                var cities = data;
                console.log(cities);
                var text = "<option selected value=\"\">Выберите город</option>";
                for (var i = 0; i !== cities.length; ++i) {
                    text += "<option value='" + cities[i] + "' name='" + cities[i] + "'>" + cities[i] + "</option>";
                }
                document.getElementById("cities").innerHTML = text;
            })
        };
    </script>

</head>
<body>
<h3>This is a old user</h3>
<table class="table table-bordered" style="border: black;" cellpadding="1" cellspacing="1" border="1">
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

<h2>Edit the user</h2>
<form class="form-horizontal" action="${pageContext.servletContext.contextPath}/edit" method="post">
    <div class="form-group">
        <label class="control-label col-sm-2" for="id">Id:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="id" name="id" placeholder="Enter id">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="name">Name:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="name" name="name" placeholder="Enter name">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="login">Login:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="login" name="login" placeholder="Enter login">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="pwd">Password:</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" id="pwd" name="password" placeholder="Enter password">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="email">Email:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="email" name="email" placeholder="Enter email">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="country">Country:</label>
        <div class="col-sm-10">
            <select id="country" name="country" class="form-control" onchange="changeCountry(this.value)">
                <option selected value="">Select country</option>
                <option value="1" id="Russia" name="Russia">Russia</option>
                <option value="2" id="USA" name="USA">USA</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="city">City:</label>
        <div class="col-sm-10" id="city">
            <select id="cities" class="form-control" name="cities">
                <option selected value="">Выберите город</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="role">Role:</label>
        <div class="col-sm-10">
            <select name="role" id="role" class="form-control">
                <option selected>Select role</option>
                <option value="admin">Admin</option>
                <option value="user">User</option>
                <option value="guest">Guest</option>
            </select>
        </div>
    </div>
    <button type="submit" class="btn btn-default" onclick="return validate()">Submit</button>
</form>
</body>
</html>
