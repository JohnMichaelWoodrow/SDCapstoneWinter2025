<%@ page import="org.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<html>
<head>
    <title>Agent - Policy Details</title>
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
        <h2>Viewing Customer Policy Details</h2>

        <%
            String policyJson = (String) request.getAttribute("policy");

            if (policyJson != null) {
                JSONObject quote = new JSONObject(policyJson);

                double price = quote.getDouble("quotePrice");
                String expiryDate = quote.optString("expiryDate", "N/A");
                String quoteType = quote.getString("quoteType");

                String detailsTable = "";

                if (quoteType.equalsIgnoreCase("Auto")) {
                    JSONObject vehicle = quote.getJSONObject("vehicle");

                    String make = vehicle.getString("make");
                    String model = vehicle.getString("model");
                    int year = vehicle.getInt("year");
                    String vin = vehicle.getString("vin");

                    detailsTable += "<tr><th>Quote Type:</th><td>Auto</td></tr>";
                    detailsTable += "<tr><th>Vehicle:</th><td>" + make + " " + model + " (" + year + ")</td></tr>";
                    detailsTable += "<tr><th>VIN:</th><td>" + vin + "</td></tr>";
                } else if (quoteType.equalsIgnoreCase("Home")) {
                    JSONObject home = quote.getJSONObject("home");

                    String address = home.getString("address");
                    int yearBuilt = home.getInt("yearBuilt");
                    double homeValue = home.getDouble("homeValue");
                    String dwelling = home.getString("typeOfDwelling");
                    String heating = home.getString("heatingType");
                    String location = home.getString("location");
                    double liabilityLimit = home.getDouble("liabilityLimit");

                    detailsTable += "<tr><th>Quote Type:</th><td>Home</td></tr>";
                    detailsTable += "<tr><th>Address:</th><td>" + address + "</td></tr>";
                    detailsTable += "<tr><th>Year Built:</th><td>" + yearBuilt + "</td></tr>";
                    detailsTable += "<tr><th>Home Value:</th><td>$" + homeValue + "</td></tr>";
                    detailsTable += "<tr><th>Type of Dwelling:</th><td>" + dwelling + "</td></tr>";
                    detailsTable += "<tr><th>Heating Type:</th><td>" + heating + "</td></tr>";
                    detailsTable += "<tr><th>Location:</th><td>" + location + "</td></tr>";
                    detailsTable += "<tr><th>Liability Limit:</th><td>$" + liabilityLimit + "</td></tr>";
                }
        %>

        <table id="quoteSummaryTable">
            <caption>Policy Summary</caption>
            <%= detailsTable %>
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
            <form action="agentDashboard.jsp" method="GET">
                <button class="quoteActionBtn" type="submit">Return to Dashboard</button>
            </form>
        </div>

        <% } else { %>
        <p style="color:red;">No policy data found.</p>
        <% } %>
    </div>
</div>
</body>
</html>
