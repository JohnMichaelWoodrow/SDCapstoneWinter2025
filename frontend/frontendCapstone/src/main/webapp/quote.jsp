<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        </div>
    </div>

    <div class="pagemaindiv">
        <h2>Happy Thursday Josh!</h2>
        <%--        <table id="quoteTable">--%>
        <%--            <caption id="quoteTableCaption">Plans for you</caption>--%>
        <%--            <tr>--%>
        <%--                <th class="quoteTableHeader">Plan</th>--%>
        <%--                <th class="quoteTableHeader">Length</th>--%>
        <%--                <th class="quoteTableHeader">Members</th>--%>
        <%--                <th class="quoteTableHeader">Cost</th>--%>
        <%--            </tr>--%>
        <%--            <tr>--%>
        <%--                <td class="quoteTableCell">Individual</td>--%>
        <%--                <td class="quoteTableCell">1 Year</td>--%>
        <%--                <td class="quoteTableCell">Max 1</td>--%>
        <%--                <td class="quoteTableCell">$500/Month</td>--%>
        <%--            </tr>--%>
        <%--            <tr>--%>
        <%--                <td class="quoteTableCell">Starter</td>--%>
        <%--                <td class="quoteTableCell">1 Year</td>--%>
        <%--                <td class="quoteTableCell">Max 2</td>--%>
        <%--                <td class="quoteTableCell">$850/Month</td>--%>
        <%--            </tr>--%>
        <%--            <tr>--%>
        <%--                <td class="quoteTableCell">Family</td>--%>
        <%--                <td class="quoteTableCell">1 Year</td>--%>
        <%--                <td class="quoteTableCell">Max 4</td>--%>
        <%--                <td class="quoteTableCell">$1700/Month</td>--%>
        <%--            </tr>--%>
        <%--            <tr>--%>
        <%--                <td class="quoteTableCell">Premium</td>--%>
        <%--                <td class="quoteTableCell">1 Year</td>--%>
        <%--                <td class="quoteTableCell">Max 8</td>--%>
        <%--                <td class="quoteTableCell">$3300/Month</td>--%>
        <%--            </tr>--%>
        <%--        </table>--%>
        <div id="getAQuoteDiv">
            <form class="getAQuoteForm">
                <h2 class="getAQuoteHeader">Get a Auto Quote</h2>
                <label for="driverAge">Driver Age: </label>
                <input class="quoteInput" type="text" id="driverAge"><br>
                <label for="accidentCount">Accident Count: </label>
                <input class="quoteInput" type="text" id="accidentCount"><br>
                <label for="vehicleAge">Vehicle Age: </label>
                <input class="quoteInput" type="text" id="vehicleAge"><br>
                <label for="vehicleValue">Vehicle Value: </label>
                <input class="quoteInput" type="text" id="vehicleValue"><br>
                <label for="total">Total: </label>
                <input class="quoteInput" type="text" id="total" disabled><br>
                <button class="calculateQuoteBtn">Calculate</button>
            </form>

            <form class="getAQuoteForm">
                <h2 class="getAQuoteHeader">Get a Home Quote</h2>
                <label for="homeValue">Home Value: </label>
                <input class="quoteInput" type="text" id="homeValue"><br>
                <label for="location">Home Location: </label>
                <input class="quoteInput" type="text" id="location"><br>
                <label for="age">Home Age: </label>
                <input class="quoteInput" type="text" id="age"><br>
                <label for="totalHome">Total: </label>
                <input class="quoteInput" type="text" id="totalHome" disabled><br>
                <button class="calculateQuoteBtn">Calculate</button>
            </form>
        </div>
    </div>

    <div class="pagemaindiv">
        <h2>Your Plans</h2>
        <table id="yourPlansTable">
            <caption id="yourPlansTableCaption">Your Current Plans</caption>
            <tr>
                <th class="quoteTableHeader">Plan</th>
                <th class="quoteTableHeader">Remaining</th>
                <th class="quoteTableHeader">Members</th>
                <th class="quoteTableHeader">Cost</th>
            </tr>
            <tr>
                <td class="quoteTableCell">Family</td>
                <td class="quoteTableCell">4 Months</td>
                <td class="quoteTableCell">3/4</td>
                <td class="quoteTableCell">$1700/Month</td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
