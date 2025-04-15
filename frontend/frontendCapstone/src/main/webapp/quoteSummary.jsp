<%@ page import="org.json.JSONObject" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    String quoteTypeVariable = (String) session.getAttribute("quoteType");
    if (quoteTypeVariable == null) {
        response.sendRedirect("quote.jsp");
        return;
    }

    Long userId = (Long) session.getAttribute("userId");
    String userRole = (String) session.getAttribute("role");

    if (!Objects.equals(userRole, "customer")) {
        response.sendRedirect("index.jsp");
        return;
    }

    String quotebtn = "";

    if (userId != null) {
        quotebtn += "<form class='quoteNavForm' action='quoteDashboard' method='GET'>" + "<input class='purchaseInput' type='hidden' name='userId' value=" + userId + ">" + "<button class='navbarbtn' type='submit'>Quote Dashboard</button>" + "</form>";
    } else {
        quotebtn += "<a class='navbarbtn' href='quote.jsp'>Get a Quote</a>";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Quote Summary</title>
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
            <form action="userProfile">
                <input value="<%= userId %>" name="userId" type="hidden">
                <button class="profileBtn" type="submit">Profile</button>
            </form>
            <a class='logoutbtn' href="logout"><i class="fa fa-sign-out"></i></a>
        </div>
    </div>

    <div id="pagemaindiv">
        <h2>Quote Summary</h2>

        <%
            String quoteJson = (String) request.getAttribute("quote");

            if (quoteJson != null) {
                JSONObject quote = new JSONObject(quoteJson);

                double price = quote.getDouble("quotePrice");
                String expiryDate = quote.optString("expiryDate", "N/A");
                String quoteType = quote.getString("quoteType");

                String summaryTable = "";
                String quoteId = "";

                if (quoteType.equalsIgnoreCase("Auto")) {
                    JSONObject vehicle = quote.getJSONObject("vehicle");

                    String make = vehicle.getString("make");
                    String model = vehicle.getString("model");
                    int year = vehicle.getInt("year");
                    String vin = vehicle.getString("vin");

                    quoteId = String.valueOf(quote.getInt("id"));

                    summaryTable += "<tr><th>Quote Type:</th><td>Auto</td></tr>";
                    summaryTable += "<tr><th>Vehicle:</th><td>" + make + " " + model + " (" + year + ")</td></tr>";
                    summaryTable += "<tr><th>VIN:</th><td>" + vin + "</td></tr>";
                } else if (quoteType.equalsIgnoreCase("Home")) {
                    JSONObject home = quote.getJSONObject("home");

                    String address = home.getString("address");
                    int yearBuilt = home.getInt("yearBuilt");
                    double homeValue = home.getDouble("homeValue");
                    String dwelling = home.getString("typeOfDwelling");
                    String heating = home.getString("heatingType");
                    String location = home.getString("location");
                    double liabilityLimit = home.getDouble("liabilityLimit");

                    quoteId = String.valueOf(quote.getInt("id"));

                    summaryTable += "<tr><th>Quote Type:</th><td>Home</td></tr>";
                    summaryTable += "<tr><th>Address:</th><td>" + address + "</td></tr>";
                    summaryTable += "<tr><th>Year Built:</th><td>" + yearBuilt + "</td></tr>";
                    summaryTable += "<tr><th>Home Value:</th><td>$" + homeValue + "</td></tr>";
                    summaryTable += "<tr><th>Type of Dwelling:</th><td>" + dwelling + "</td></tr>";
                    summaryTable += "<tr><th>Heating Type:</th><td>" + heating + "</td></tr>";
                    summaryTable += "<tr><th>Location:</th><td>" + location + "</td></tr>";
                    summaryTable += "<tr><th>Liability Limit:</th><td>$" + liabilityLimit + "</td></tr>";
                }
        %>

        <table id="quoteSummaryTable">
            <caption>Quote Summary</caption>
            <%= summaryTable %>
            <tr>
                <th>Total:</th>
                <td style="color:green;">$<%= price %></td>
            </tr>
            <tr>
                <th>Expiry Date:</th>
                <td><%= expiryDate %></td>
            </tr>
            <tr>
                <th>Payment Date:</th>
                <td>N/A</td>
            </tr>
        </table>

        <div id="quoteActionDiv">
            <form id="purchaseForm" method="POST" action="purchase">

                <input class="purchaseInput" type="hidden" id="id" name="id" value="<%= quoteId %>"><br>
                <input class="purchaseInput" type="hidden" id="userId" name="userId" value="<%= userId %>"><br>
                <button class="loginregbtn" type="submit">Purchase</button>
            </form>
            <form action="deleteQuote" method="POST" onsubmit="return confirm('Are you sure you want to delete this quote?');">
                <input type="hidden" name="quoteId" value="<%= quoteId %>">
                <button class="loginregbtn" type="submit">Delete</button>
            </form>
            <form action="quoteDashboard" method="GET">
                <input class="purchaseInput" type="hidden" name="userId" value="<%= userId %>">
                <button class="quoteActionBtn" type="submit">Cancel</button>
            </form>
            <%--            <button class="quoteActionBtn" onclick="window.location.href='quote.jsp'">Cancel</button>--%>
        </div>

        <% } else { %>
        <p style="color:red;">No quote data found.</p>
        <% } %>

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
