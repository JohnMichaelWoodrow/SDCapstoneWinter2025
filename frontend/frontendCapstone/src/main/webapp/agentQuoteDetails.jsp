<%@ page import="org.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html>
<head>
    <title>Agent – Quote Details</title>
    <link href="style.css" rel="stylesheet" type="text/css">
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
    </div>

    <div id="pagemaindiv">
        <h2>Viewing Quote Details</h2>

        <%
            String quoteJson = (String) request.getAttribute("quote");

            if (quoteJson != null) {
                JSONObject quote = new JSONObject(quoteJson);
                int quoteId = quote.getInt("id");

                double price = quote.getDouble("quotePrice");
                String expiryDate = quote.optString("expiryDate", "N/A");
                String quoteType = quote.getString("quoteType");

                String summaryTable = "";

                if (quoteType.equalsIgnoreCase("Auto")) {
                    JSONObject vehicle = quote.getJSONObject("vehicle");

                    String make = vehicle.getString("make");
                    String model = vehicle.getString("model");
                    int year = vehicle.getInt("year");
                    String vin = vehicle.getString("vin");

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
            <form action="deleteQuote" method="POST" onsubmit="return confirm('Are you sure you want to delete this quote?');">
                <input type="hidden" name="quoteId" value="<%= quoteId %>">
                <button class="loginregbtn" type="submit">Delete Quote</button>
            </form>

            <form action="agentDashboard.jsp" method="GET">
                <button class="quoteActionBtn" type="submit">Return to Dashboard</button>
            </form>
        </div>

        <% } else { %>
        <p style="color:red;">No quote data found.</p>
        <% } %>
    </div>
</div>
</body>
</html>
