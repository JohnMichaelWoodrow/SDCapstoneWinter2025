<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    String agentName = (String) session.getAttribute("name");
    if (agentName == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Agent Dashboard</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="images/TaylorIns-inv.png">
</head>
<body>
<div id="maindiv">
    <div id="navbar">
        <a href="index.jsp"><img id="navbarimg" src="images/TaylorIns-inv.png"></a>
        <div id="navbarbuttons">
            <a class="navbarbtn" href="index.jsp">Home</a>
            <a class="navbarbtn" href="agentDashboard.jsp">Dashboard</a>
            <a class="navbarbtn" href="about.jsp">About Us</a>
        </div>
        <div id="navbarlogin">
            <a href="login.jsp"><img id="profileimg" src="images/profile.jpg"></a>
            <a href="logout">Logout</a>
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
