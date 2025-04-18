<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    String userRole = (String) session.getAttribute("role");
    Long userId = (Long) session.getAttribute("userId");

    String quotebtn = "";
    String logoutbtn = "";
    String profilebtn = "";

    if (Objects.equals(userRole, "customer")) {
        quotebtn += "<form class='quoteNavForm' action='quoteDashboard' method='GET'>" + "<input class='purchaseInput' type='hidden' name='userId' value=" + userId + ">" + "<button class='navbarbtn' type='submit'>Quote Dashboard</button>" + "</form>";
        logoutbtn += "<a class='logoutbtn' href='logout'><i class='fa fa-sign-out'></i></a>";
        profilebtn += "<form action='userProfile'><input value= " +  userId + " name='userId' type='hidden'><button class='profileBtn' type='submit'>Profile</button></form>";
    } else if (Objects.equals(userRole, "agent")) {
        quotebtn += "<a class='navbarbtn' href='agentDashboard.jsp'>Agent Dashboard</a>";
        logoutbtn += "<a class='logoutbtn' href='logout'><i class='fa fa-sign-out'></i></a>";
        profilebtn += "<form action='userProfile'><input value= " +  userId + " name='userId' type='hidden'><button class='profileBtn' type='submit'>Profile</button></form>";
    } else {
        quotebtn += "<a class='navbarbtn' href='quote.jsp'>Get a Quote</a>";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="images/TaylorIns-inv.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div id="maindiv">
    <div id="navbar">
        <a href="index.jsp"><img id="navbarimg" src="images/TaylorIns-inv.png"></a>
        <div id="navbarbuttons">
            <a class="navbarbtn" href="index.jsp">Home</a>
            <%= quotebtn %>
            <a class="navbarbtn" href="about.jsp">About Us</a>
            <a class="navbarbtn" href="contact.jsp">Contact Us</a>
        </div>
        <div id="navbarlogin">
            <%= profilebtn %>
            <%= logoutbtn %>
        </div>
    </div>

    <div id="pagemaindiv">
        <h2>Welcome!</h2>
        <p>We take pride in having the best coverage for you and your family.</p>
        <div id="createAccountLink">
            <h3>Get a Quote now!</h3>
            <%= quotebtn %>
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