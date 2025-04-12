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
%>
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
            <a class="navbarbtn" href="quote.jsp">Get a Quote</a>
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
        %>
        <h2>Happy Thursday <%=user%></h2>
        <div id="getAQuoteDiv">

            <form class="getAQuoteForm" action="get-quote" method="POST">
                <h2 class="getAQuoteHeader">Get an Auto Quote</h2>

                <label for="make">Vehicle Make:</label>
                <input class="quoteInput" type="text" id="make" name="make"><br>

                <label for="model">Vehicle Model:</label>
                <input class="quoteInput" type="text" id="model" name="model"><br>

                <label for="year">Vehicle Year:</label>
                <input class="quoteInput" type="number" id="year" name="year"><br>

                <label for="vin">Vehicle VIN:</label>
                <input class="quoteInput" type="text" id="vin" name="vin"><br>

                <label for="driverAge">Driver Age:</label>
                <input class="quoteInput" type="number" id="driverAge" name="driverAge"><br>

                <label for="accidentCount">Accident Count:</label>
                <input class="quoteInput" type="number" id="accidentCount" name="accidentCount"><br>

                <button class="calculateQuoteBtn" type="submit">Calculate</button>
            </form>

            <form class="getAQuoteForm" action="get-home-quote" method="POST" id="homeQuoteForm">
                <h2 class="getAQuoteHeader">Get a Home Quote</h2>

                <label for="address">Address:</label>
                <input class="quoteInput" type="text" id="address" name="address"><br>

                <label for="yearBuilt">Year Built:</label>
                <input class="quoteInput" type="number" id="yearBuilt" name="yearBuilt"><br>

                <label for="homeValue">Home Value:</label>
                <input class="quoteInput" type="number" id="homeValue" name="homeValue" step="1000"><br>

                <label for="typeOfDwelling">Type of Dwelling:</label>
                <input class="quoteInput" type="text" id="typeOfDwelling" name="typeOfDwelling"><br>

                <label for="heatingType">Heating Type:</label>
                <select class="quoteInput" id="heatingType" name="heatingType">
                    <option value="Oil">Oil</option>
                    <option value="Wood">Wood</option>
                    <option value="Other">Other</option>
                </select><br>

                <label for="location">Home Location:</label>
                <select class="quoteInput" id="location" name="location">
                    <option value="Urban">Urban</option>
                    <option value="Rural">Rural</option>
                </select><br>

                <label for="liabilityLimit">Liability Limit:</label>
                <input class="quoteInput" type="number" id="liabilityLimit" name="liabilityLimit" step="10000"><br>

                <button class="calculateQuoteBtn" type="submit">Calculate</button>
            </form>


        </div>
    </div>

    <div class="pagemaindiv">
        <h2>Your Policies</h2>
        <table id="yourPlansTable">
            <caption id="yourPlansTableCaption">Your Current Policies</caption>
            <tr>
                <th class="quoteTableHeader">Type</th>
                <th class="quoteTableHeader">Remaining</th>
                <th class="quoteTableHeader">Date Paid</th>
                <th class="quoteTableHeader">Cost</th>
            </tr>
            <tr>
                <td class="quoteTableCell">Auto</td>
                <td class="quoteTableCell">4 Months</td>
                <td class="quoteTableCell">2024-01-01</td>
                <td class="quoteTableCell">$750</td>
            </tr>
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
