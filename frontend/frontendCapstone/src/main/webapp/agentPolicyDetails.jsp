<%@ page import="org.json.JSONObject" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    String userRole = (String) session.getAttribute("role");
    if (!Objects.equals(userRole, "agent")) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
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
            <a class="navbarbtn" href="agentDashboard.jsp">Agent Dashboard</a>
            <a class="navbarbtn" href="about.jsp">About Us</a>
        </div>
    </div>

    <div id="pagemaindiv">
        <h2>Viewing Customer Policy Details</h2>

        <%
            String quoteJson = (String) request.getAttribute("quote");
            String policyJson = (String) request.getAttribute("policy");

            JSONObject quote = new JSONObject(quoteJson);
            JSONObject policy = new JSONObject(policyJson);

            int quoteId = quote.getInt("id");

            if (quoteJson != null) {

                double price = policy.getDouble("totalPremium");
                double basePrice = policy.getDouble("basePremium");

                String startDate = policy.getString("startDate");
                String endDate = policy.getString("endDate");

                String quoteType = quote.getString("quoteType");

                String detailsTable = "";

                if (quoteType.equalsIgnoreCase("Auto")) {
                    JSONObject vehicle = quote.getJSONObject("vehicle");

                    String make = vehicle.getString("make");
                    String model = vehicle.getString("model");
                    int year = vehicle.getInt("year");
                    String vin = vehicle.getString("vin");

                    detailsTable += "<tr><th>Policy Type:</th><td>Auto</td></tr>";
                    detailsTable += "<tr><th>Vehicle:</th><td>" + make + " " + model + " (" + year + ")</td></tr>";
                    detailsTable += "<tr><th>VIN:</th><td>" + vin + "</td></tr>";
                    detailsTable += "<tr><th>Base Premium:</th><td>" + basePrice + "</td></tr>";
                } else if (quoteType.equalsIgnoreCase("Home")) {
                    JSONObject home = quote.getJSONObject("home");

                    String address = home.getString("address");
                    int yearBuilt = home.getInt("yearBuilt");
                    double homeValue = home.getDouble("homeValue");
                    String dwelling = home.getString("typeOfDwelling");
                    String heating = home.getString("heatingType");
                    String location = home.getString("location");
                    double liabilityLimit = home.getDouble("liabilityLimit");

                    detailsTable += "<tr><th>Policy Type:</th><td>Home</td></tr>";
                    detailsTable += "<tr><th>Address:</th><td>" + address + "</td></tr>";
                    detailsTable += "<tr><th>Year Built:</th><td>" + yearBuilt + "</td></tr>";
                    detailsTable += "<tr><th>Home Value:</th><td>$" + homeValue + "</td></tr>";
                    detailsTable += "<tr><th>Type of Dwelling:</th><td>" + dwelling + "</td></tr>";
                    detailsTable += "<tr><th>Heating Type:</th><td>" + heating + "</td></tr>";
                    detailsTable += "<tr><th>Location:</th><td>" + location + "</td></tr>";
                    detailsTable += "<tr><th>Liability Limit:</th><td>$" + liabilityLimit + "</td></tr>";
                    detailsTable += "<tr><th>Base Premium:</th><td>$" + basePrice + "</td></tr>";
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
                <th>Start Date:</th>
                <td><%= startDate %></td>
            </tr>
            <tr>
                <th>End Date:</th>
                <td><%= endDate %></td>
            </tr>
        </table>

        <div id="quoteActionDiv">
            <form action="deletePolicy" method="POST" onsubmit="return confirm('Are you sure you want to cancel this policy?');">
                <input type="hidden" name="policyId" value="<%= request.getParameter("policyId") %>">
                <input type="hidden" name="quoteId" value="<%= quoteId %>">
                <button class="loginregbtn" type="submit">Cancel Policy</button>
            </form>

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
