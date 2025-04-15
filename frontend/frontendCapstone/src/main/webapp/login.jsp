<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    Long userId = (Long) session.getAttribute("userId");
    String userRole = (String) session.getAttribute("role");

    String quotebtn = "";

    if (userId != null) {
        quotebtn += "<form class='quoteNavForm' action='cancelQuote' method='GET'>" + "<input class='purchaseInput' type='hidden' name='userId' value=" + userId + ">" + "<button class='navbarbtn' type='submit'>Get a Quote</button>" + "</form>";
    } else {
        quotebtn += "<a class='navbarbtn' href='quote.jsp'>Get a Quote</a>";
    }

    if (Objects.equals(userRole, "customer")) {
        response.sendRedirect("index.jsp");
        return;
    } else if (Objects.equals(userRole, "agent")) {
        response.sendRedirect("index.jsp");
        return;
    }

%>
<!DOCTYPE html>
<html>
<head>
    <title>Login/Register</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="images/TaylorIns-inv.png">
</head>
<body>
<div id="maindiv">
    <div id="navbar">
        <a href="index.jsp"><img id="navbarimg" src="images/TaylorIns-inv.png"></a>
        <div id="navbarbuttons">
            <a class="navbarbtn" href="index.jsp">Home</a>
            <%= quotebtn %>
            <a class="navbarbtn" href="about.jsp">About Us</a>
        </div>
        <div id="navbarlogin">

        </div>
    </div>

    <div id="pagemaindiv">
        <div id="loginregdiv">
            <form id="loginform" method="POST" action="login">
                <h2>Login</h2>
                <label for="logemail">Email: </label>
                <input class="logininput" type="text" id="logemail" name="email"><br>
                <label for="logpassword">Password: </label>
                <input class="logininput" type="password" id="logpassword" name="password"><br>
                <button class="loginregbtn" type="submit">Login</button>
            </form>

            <% String error = (String) request.getAttribute("error"); %>
            <% if (error != null) { %>
            <div style="color:red;"><%= error %></div>
            <% } %>

            <h2>Or</h2>

            <form id="registerform" method="POST" action="register">
                <h2>Register</h2>
                <label for="fname">First Name: </label>
                <input class="logininput" type="text" id="fname" name="fname"><br>

                <label for="lname">Last Name: </label>
                <input class="logininput" type="text" id="lname" name="lname"><br>

                <label for="regemail">Email: </label>
                <input class="logininput" type="text" id="regemail" name="regemail"><br>

                <label for="regpassword">Password: </label>
                <input class="logininput" type="password" id="regpassword" name="regpassword"><br>

                <label for="confpassword">Confirm Password: </label>
                <input class="logininput" type="password" id="confpassword" name="confpassword"><br>

                <button class="loginregbtn" type="submit">Register</button>
            </form>

            <% String registerError = (String) request.getAttribute("registerError"); %>
            <% if (registerError != null) { %>
            <div style="color:red;"><%= registerError %></div>
            <% } %>

            <% String registerSuccess = (String) request.getAttribute("registerSuccess"); %>
            <% if (registerSuccess != null) { %>
            <div style="color:green; font-weight: bold;"><%= registerSuccess %></div>
            <% } %>

        </div>
    </div>
</div>

</body>
<footer>
    <div id="contactDiv">
        <h3>Contact Us</h3>
        <p>Email: support@taylorinsurance.com</p>
        <p>Phone: +1 (709) 123-4567</p>
    </div>
</footer>
</html>
