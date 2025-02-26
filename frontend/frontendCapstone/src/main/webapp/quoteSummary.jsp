<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>About</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="images/TaylorIns-inv.png">
</head>
<body>
<div id="maindiv">
    <div id="navbar">
        <a href="index.jsp"><img id="navbarimg" src="images/TaylorIns-inv.png"></a>
        <div id="navbarbuttons">
            <a class="navbarbtn" href="index.jsp">Home</a>
            <a class="navbarbtn" href="login.jsp">Get a Quote</a>
            <a class="navbarbtn" href="index.jsp">About Us</a>
        </div>
        <div id="navbarlogin">
            <a href="login.jsp"><img id="profileimg" src="images/profile.jpg"></a>
        </div>
    </div>

    <div id="pagemaindiv">
        <h2>Quote Summary</h2>
        <table id="quoteSummaryTable">
            <caption>Quote Summary</caption>
            <tr>
                <th>Quote Type: </th>
                <td>Auto</td>
            </tr>
            <tr>
                <th>Expiry Date: </th>
                <td>2026-01-01</td>
            </tr>
            <tr>
                <th>Total: </th>
                <td>$750</td>
            </tr>
            <tr>
                <th>Payment Date: </th>
                <td>N/A</td>
            </tr>
        </table>
        <div id="quoteActionDiv">
            <button class="quoteActionBtn">Purchase</button>
            <button class="quoteActionBtn">Cancel</button>
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
