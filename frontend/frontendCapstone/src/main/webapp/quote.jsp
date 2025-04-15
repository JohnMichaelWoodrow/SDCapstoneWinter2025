<%@ page import="org.json.JSONObject" %>
<%@ page import="org.json.JSONArray" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    Long userId = (Long) session.getAttribute("userId");
    if (userId == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String quotebtn = "";

    if (userId != null) {
        quotebtn += "<form class='quoteNavForm' action='cancelQuote' method='GET'>" + "<input class='purchaseInput' type='hidden' name='userId' value=" + userId + ">" + "<button class='navbarbtn' type='submit'>Get a Quote</button>" + "</form>";
    } else {
        quotebtn += "<a class='navbarbtn' href='quote.jsp'>Get a Quote</a>";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Get a Quote</title>
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
            <a href="login.jsp"><img id="profileimg" src="images/Josh.png"></a>
            <a href="logout">Logout</a>
        </div>
    </div>

    <div class="pagemaindiv">
        <%
            String userName = (String) session.getAttribute("name");
            String user = null;
            if (userName != null) {
                user = userName;
            }
            java.time.DayOfWeek dayOfWeek = java.time.LocalDate.now().getDayOfWeek();
        %>

        <h2>Happy <%= dayOfWeek.toString().charAt(0) + dayOfWeek.toString().substring(1).toLowerCase() %> <%= user %>!</h2>

        <div id="getAQuoteDiv">

            <form class="getAQuoteForm" action="get-quote" method="POST">
                <h2 class="getAQuoteHeader">Get an Auto Quote</h2>

                <label for="make">Vehicle Make:</label>
                <input class="quoteInput" type="text" id="make" name="make" required><br>

                <label for="model">Vehicle Model:</label>
                <input class="quoteInput" type="text" id="model" name="model" required><br>

                <label for="year">Vehicle Year:</label>
                <input class="quoteInput" type="number" id="year" name="year" required><br>

                <label for="vin">Vehicle VIN:</label>
                <input class="quoteInput" type="text" id="vin" name="vin" required><br>

                <label for="driverAge">Driver Age:</label>
                <input class="quoteInput" type="number" id="driverAge" name="driverAge" required><br>

                <label for="accidentCount">Accident Count:</label>
                <input class="quoteInput" type="number" id="accidentCount" name="accidentCount" required><br>

                <button class="calculateQuoteBtn" type="submit">Calculate</button>
            </form>

            <form class="getAQuoteForm" action="get-home-quote" method="POST" id="homeQuoteForm">
                <h2 class="getAQuoteHeader">Get a Home Quote</h2>

                <label for="address">Address:</label>
                <input class="quoteInput" type="text" id="address" name="address" required><br>

                <label for="yearBuilt">Year Built:</label>
                <input class="quoteInput" type="number" id="yearBuilt" name="yearBuilt" required><br>

                <label for="homeValue">Home Value:</label>
                <input class="quoteInput" type="number" id="homeValue" name="homeValue" step="1000" required><br>

                <label for="typeOfDwelling">Type of Dwelling:</label>
                <input class="quoteInput" type="text" id="typeOfDwelling" name="typeOfDwelling" required><br>

                <label for="heatingType">Heating Type:</label>
                <select class="quoteInput" id="heatingType" name="heatingType" required>
                    <option value="">-- Select Heating Type --</option>
                    <option value="Oil">Oil</option>
                    <option value="Wood">Wood</option>
                    <option value="Other">Other</option>
                </select><br>

                <label for="location">Home Location:</label>
                <select class="quoteInput" id="location" name="location" required>
                    <option value="">-- Select Location --</option>
                    <option value="Urban">Urban</option>
                    <option value="Rural">Rural</option>
                </select><br>

                <label for="liabilityLimit">Liability Limit:</label>
                <input class="quoteInput" type="number" id="liabilityLimit" name="liabilityLimit" required><br>

                <button class="calculateQuoteBtn" type="submit">Calculate</button>
            </form>


        </div>
        <%
            String quoteError = (String) request.getAttribute("quoteError");
            if (quoteError != null) {
        %>
        <div style="color: red; font-weight: bold; text-align: center">
            <%= quoteError %>
        </div>
        <%
            }
        %>
    </div>

    <div class="pagemaindiv">
        <h2>Your Policies</h2>
        <%
            String policyJson = (String) request.getAttribute("policies");

            String summaryTable = "";
            String quotesTable = "";

            if (policyJson != null) {
                JSONObject customer = new JSONObject(policyJson);
                JSONArray policies = customer.getJSONArray("policies");

                JSONArray quotes = customer.getJSONArray("quotes");

                summaryTable += "<tr><th class='quoteTableHeader'>Type</th><th class='quoteTableHeader'>Start Date</th><th class='quoteTableHeader'>End Date</th><th class='quoteTableHeader'>Status</th><th class='quoteTableHeader'>Total Premium</th><th class='quoteTableHeader'>Details</th></tr>";

                for (int i = 0; i < policies.length(); i++) {
                    JSONObject policy = policies.getJSONObject(i);

                    String policyType = policy.getString("policyType");
                    String startDate = policy.getString("startDate");
                    String endDate = policy.getString("endDate");
                    String status = policy.getString("status");
                    double totalPremium = policy.getDouble("totalPremium");

                    int policyId = policy.getInt("id");

                    summaryTable += "<tr>";
                    summaryTable += "<td class='quoteTableCell'>" + policyType + "</td>";
                    summaryTable += "<td class='quoteTableCell'>" + startDate + "</td>";
                    summaryTable += "<td class='quoteTableCell'>" + endDate + "</td>";
                    summaryTable += "<td class='quoteTableCell'>" + status + "</td>";
                    summaryTable += "<td class='quoteTableCell'>$" + totalPremium + "</td>";
                    summaryTable += "<td class='quoteTableCell'>" +
                            "<form class='hiddenQuoteForm' action='getPolicy' method='GET'>" +
                            "<input type='hidden' name='policyId' value='" + policyId + "'>" +
                            "<button class='getQuoteBtn' type='submit'>Details</button>" +
                            "</form></td>";
                    summaryTable += "</tr>";

                }

                quotesTable += "<tr><th class='quoteTableHeader'>Type</th><th class='quoteTableHeader'>Expires</th><th class='quoteTableHeader'>Price</th><th class='quoteTableHeader'>Purchase</th></tr>";

                for (int i = 0; i < quotes.length(); i++) {
                    JSONObject quote = quotes.getJSONObject(i);

                    int quoteId = quote.getInt("id");
                    String quoteType = quote.getString("quoteType");
                    String expiryDate = quote.getString("expiryDate");
                    double quotePrice = quote.getDouble("quotePrice");
                    Boolean paid = quote.getBoolean("paid");
                    Boolean expired = quote.getBoolean("expired");


                    if (!paid && !expired) {
                        quotesTable += "<tr>";
                        quotesTable += "<td class='quoteTableCell'>" + quoteType + "</td>";
                        quotesTable += "<td class='quoteTableCell'>" + expiryDate + "</td>";
                        quotesTable += "<td class='quoteTableCell'>" + quotePrice + "</td>";
                        quotesTable += "<td class='quoteTableCell'>" +
                                "<form class='hiddenQuoteForm' action='getQuote' method='GET'>" +
                                "<input class='hiddenQuoteInput' type='hidden' name='quoteId' value=" + quoteId + ">" +
                                "<input class='hiddenQuoteInput' type='hidden' name='quoteType' value=" + quoteType + ">" +
                                "<button class='getQuoteBtn' type='submit'>Purchase</button>" +
                                "</form></td>";
                        quotesTable += "</tr>";
                    }
                }
            }
        %>
        <table class="yourPlansTable">
            <%=summaryTable%>
        </table><br>
    </div>
    <div class="pagemaindiv">
        <h2>Your Quotes</h2>
        <table class="yourPlansTable">
            <%=quotesTable%>
        </table>
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
