<%@ page import="org.json.JSONObject" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    Long agentId = (Long) session.getAttribute("userId");
    String userRole = (String) session.getAttribute("role");
    if (agentId == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    if (!Objects.equals(userRole, "agent")) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer View</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="images/TaylorIns-inv.png">
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
        <div id="navbarlogin">
            <form action="userProfile">
                <input value="<%= agentId %>" name="userId" type="hidden">
                <button class="profileBtn" type="submit">Profile</button>
            </form>
            <a class='logoutbtn' href="logout"><i class="fa fa-sign-out"></i></a>
        </div>
    </div>

    <div class="pagemaindiv">
        <h2>Viewing Customer ID <%= request.getAttribute("customerId") %></h2>
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

                quotesTable += "<tr><th class='quoteTableHeader'>Type</th><th class='quoteTableHeader'>Expires</th><th class='quoteTableHeader'>Price</th><th class='quoteTableHeader'>Details</th></tr>";

                for (int i = 0; i < quotes.length(); i++) {
                    JSONObject quote = quotes.getJSONObject(i);
                    int quoteId = quote.getInt("id");
                    String quoteType = quote.getString("quoteType");
                    String expiryDate = quote.getString("expiryDate");
                    double quotePrice = quote.getDouble("quotePrice");
                    boolean paid = quote.getBoolean("paid");
                    boolean expired = quote.getBoolean("expired");

                    if (!paid && !expired) {
                        quotesTable += "<tr>";
                        quotesTable += "<td class='quoteTableCell'>" + quoteType + "</td>";
                        quotesTable += "<td class='quoteTableCell'>" + expiryDate + "</td>";
                        quotesTable += "<td class='quoteTableCell'>$" + quotePrice + "</td>";
                        quotesTable += "<td class='quoteTableCell'>" +
                                "<form class='hiddenQuoteForm' action='getQuote' method='GET'>" +
                                "<input type='hidden' name='quoteId' value=" + quoteId + ">" +
                                "<input type='hidden' name='quoteType' value=" + quoteType + ">" +
                                "<button class='getQuoteBtn' type='submit'>Details</button>" +
                                "</form></td>";
                        quotesTable += "</tr>";
                    }
                }
            }
        %>

        <h3 style="text-align: center;">Customer Policies</h3>
        <table class="yourPlansTable">
            <%= summaryTable %>
        </table>

        <br>

        <h3 style="text-align: center;">Customer Quotes</h3>
        <table class="yourPlansTable">
            <%= quotesTable %>
        </table>

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
