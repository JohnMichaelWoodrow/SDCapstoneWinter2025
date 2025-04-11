<%@ page import="org.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <a class="navbarbtn" href="quote.jsp">Get a Quote</a>
            <a class="navbarbtn" href="about.jsp">About Us</a>
        </div>
        <div id="navbarlogin">
            <a href="login.jsp"><img id="profileimg" src="images/profile.jpg"></a>
        </div>
    </div>

    <div id="pagemaindiv">
        <h2>Quote Summary</h2>

        <%
            String quoteJson = (String) request.getAttribute("quote");

            if (quoteJson != null) {
                JSONObject quote = new JSONObject(quoteJson);

                double price = quote.getDouble("quotePrice");
                String expiryDate = quote.getString("expiryDate");
                String quoteType = quote.getString("quoteType");

                String summaryTable = "";

                if (quoteType.equalsIgnoreCase("Auto")) {
                    JSONObject vehicle = quote.getJSONObject("vehicle");
                    double vehicleValue = vehicle.getDouble("value");

                    summaryTable += "<tr><th>Quote Type:</th><td>Auto</td></tr>";
                    summaryTable += "<tr><th>Vehicle Value:</th><td>$" + vehicleValue + "</td></tr>";

                } else if (quoteType.equalsIgnoreCase("Home")) {
                    JSONObject home = quote.getJSONObject("home");
                    double homeValue = home.getDouble("homeValue");
                    String heating = home.getString("heatingType");
                    String location = home.getString("location");

                    summaryTable += "<tr><th>Quote Type:</th><td>Home</td></tr>";
                    summaryTable += "<tr><th>Home Value:</th><td>$" + homeValue + "</td></tr>";
                    summaryTable += "<tr><th>Location:</th><td>" + location + "</td></tr>";
                    summaryTable += "<tr><th>Heating Type:</th><td>" + heating + "</td></tr>";
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
            <button class="quoteActionBtn">Purchase</button>
            <button class="quoteActionBtn" onclick="window.location.href='quote.jsp'">Cancel</button>
        </div>

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
