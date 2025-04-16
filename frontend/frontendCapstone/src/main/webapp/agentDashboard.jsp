<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    Long userId = (Long) session.getAttribute("userId");

    String userRole = (String) session.getAttribute("role");
    String agentName = (String) session.getAttribute("name");
    if (!Objects.equals(userRole, "agent")) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Agent Dashboard</title>
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
            <a class="navbarbtn" href="agentDashboard.jsp">Agent Dashboard</a>
            <a class="navbarbtn" href="about.jsp">About Us</a>
            <a class="navbarbtn" href="contact.jsp">Contact Us</a>
        </div>
        <div id="navbarlogin">
            <form action="userProfile">
                <input value="<%= userId %>" name="userId" type="hidden">
                <button class="profileBtn" type="submit">Profile</button>
            </form>
            <a class='logoutbtn' href="logout"><i class="fa fa-sign-out"></i></a>
        </div>
    </div>

    <div class="pagemaindiv">
        <h2>Welcome <%= agentName %></h2>

        <div id="quoteActionDiv">
            <h2>Lookup Customer Dashboard</h2>
            <form action="viewCustomer" method="GET">
                <label for="customerId">Customer ID:</label>
                <input type="number" id="customerId" name="customerId" required>
                <button type="submit">View</button>
            </form>
        </div>

        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
        <div style="color: red; font-weight: bold; text-align: center">
            <%= error %>
        </div>
        <%
            }
        %>

    </div>
</div>

<footer>
    <div id="contactDiv">
        <h3>Contact Us</h3>
        <p>Email: support@taylorinsurance.com</p>
        <p>Phone: +1 (709) 123-4567</p>
    </div>
</footer>
</body>
</html>
