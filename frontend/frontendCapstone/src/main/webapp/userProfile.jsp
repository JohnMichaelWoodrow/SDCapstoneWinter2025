<%@ page import="java.util.Objects" %>
<%@ page import="org.json.JSONObject" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    String userRole = (String) session.getAttribute("role");
    Long userId = (Long) session.getAttribute("userId");
    String name = (String) session.getAttribute("name");

    if (userId == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String quotebtn = "";
    String logoutbtn = "";

    if (Objects.equals(userRole, "customer")) {
        quotebtn += "<form class='quoteNavForm' action='quoteDashboard' method='GET'>" + "<input class='purchaseInput' type='hidden' name='userId' value=" + userId + ">" + "<button class='navbarbtn' type='submit'>Quote Dashboard</button>" + "</form>";
        logoutbtn += "<a class='logoutbtn' href='logout'><i class='fa fa-sign-out'></i></a>";
    } else if (Objects.equals(userRole, "agent")) {
        quotebtn += "<a class='navbarbtn' href='agentDashboard.jsp'>Agent Dashboard</a>";
        logoutbtn += "<a class='logoutbtn' href='logout'><i class='fa fa-sign-out'></i></a>";
    } else {
        quotebtn += "<a class='navbarbtn' href='quote.jsp'>Get a Quote</a>";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
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
            <%= logoutbtn %>
        </div>
    </div>

    <div id="userProfileDiv">
        <h2 class="userProfileHeader"><%= name %></h2>
        <div class="profileImg">
            <img src="images/profile.jpg">
        </div>
        <%
            String userJson = (String) request.getAttribute("customer");

            String userName = "";
            String userEmail = "";
            String userPassword = "";

            if (userJson != null) {
                JSONObject user = new JSONObject(userJson);

                userName = user.getString("name");
                userEmail = user.getString("email");
                userPassword = user.getString("password");
            }
        %>
        <div class="profileUpdateDiv">
            <form class="profileUpdateForm" action="updateUserProfile" method="POST">
                <p>Name: <%= userName %></p>
                <label for="newName">Update Name: </label>
                <input id="newName" type="text" name="newName"><br>
                <p>Email: <%= userEmail %></p>
                <label for="newEmail">Update Email: </label>
                <input id="newEmail" type="text" name="newEmail"><br><br>
                <label for="newPassword">Update Password: </label>
                <input id="newPassword" type="text" name="newPassword"><br>
                <input id="currentName" name="currentName" type="hidden" value="<%= userName %>" >
                <input id="currentEmail" name="currentEmail" type="hidden" value="<%= userEmail %>" >
                <input id="currentPassword" name="currentPassword" type="hidden" value="<%= userPassword %>" >
                <input id="userId" name="userId" type="hidden" value="<%= userId %>" >
                <button class="updateProfileBtn" type="submit">Update Profile</button>
            </form>
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
