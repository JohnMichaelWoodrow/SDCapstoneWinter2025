<%--
  Created by IntelliJ IDEA.
  User: lucas_aop04az
  Date: 2025-02-24
  Time: 6:41 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login/Register</title>
    <link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="maindiv">
    <div id="navbar">
        <img id="navbarimg" src="images/Mergabyte_processed.png">
        <div id="navbarbuttons">
            <a class="navbarbtn" href="index.jsp">Home</a>
            <a class="navbarbtn" href="index.jsp">Test</a>
            <a class="navbarbtn" href="index.jsp">About Us</a>
        </div>
        <div id="navbarlogin">
            <a href="login.jsp"><img id="profileimg" src="images/profile.jpg"></a>
        </div>
    </div>

    <div id="pagemaindiv">
        <div id="loginregdiv">
            <form id="loginform">
                <h2>Login</h2>
                <label for="logemail">Email: </label>
                <input class="logininput" type="text" id="logemail"><br>
                <label for="logpassword">Password: </label>
                <input class="logininput" type="text" id="logpassword"><br>
                <button class="loginregbtn" type="submit">Login</button>
            </form>
            <h2>Or</h2>
            <form id="registerform">
                <h2>Register</h2>
                <label for="fname">First Name: </label>
                <input class="logininput" type="text" id="fname"><br>
                <label for="lname">Last Name: </label>
                <input class="logininput" type="text" id="lname"><br>
                <label for="regemail">Email: </label>
                <input class="logininput" type="text" id="regemail"><br>
                <label for="address">Address: </label>
                <input class="logininput" type="text" id="address"><br>
                <label for="city">City: </label>
                <input class="logininput" type="text" id="city"><br>
                <label for="provinceState">Province/State: </label>
                <input class="logininput" type="text" id="provinceState"><br>
                <label for="regpassword">Password: </label>
                <input class="logininput" type="text" id="regpassword"><br>
                <label for="confpassword">Confirm Password: </label>
                <input class="logininput" type="text" id="confpassword"><br>
                <button class="loginregbtn" type="submit">Register</button>
            </form>
        </div>
    </div>
</div>


</body>
</html>
