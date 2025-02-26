<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="images/TaylorIns-inv.png">
</head>
<body>
<div id="maindiv">
    <div id="navbar">
        <a href="index.jsp"><img id="navbarimg" src="images/TaylorIns-inv.png"></a>
        <div id="navbarbuttons">
            <a class="navbarbtn" href="index.jsp">Home</a>
            <a class="navbarbtn" href="login.jsp">Get a Quote</a>
            <a class="navbarbtn" href="about.jsp">About Us</a>
        </div>
        <div id="navbarlogin">
            <a href="login.jsp"><img id="profileimg" src="images/profile.jpg"></a>
        </div>
    </div>

    <div id="pagemaindiv">
        <h2>Welcome!</h2>
        <p>We take pride in having the best coverage for you and your family.</p>
        <div id="createAccountLink">
            <h3>Create an account to get started!</h3>
            <a id="createAccountbtn" href="login.jsp">Create Account</a>
        </div>
    </div>
</div>
<%--<footer>--%>
<%--    <h2>foot</h2>--%>
<%--</footer>--%>

</body>
<footer>
    <div id="contactDiv">
        <h3>Contact Us</h3>
        <p>Email: support@taylorinsurance.com</p>
        <p>Phone: +1 (709) 123-4567</p>
    </div>
</footer>
</html>