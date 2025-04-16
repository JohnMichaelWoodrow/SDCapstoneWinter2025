<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    String userRole = (String) session.getAttribute("role");
    Long userId = (Long) session.getAttribute("userId");

    String quotebtn = "";
    String logoutbtn = "";
    String profilebtn = "";

    if (Objects.equals(userRole, "customer")) {
        quotebtn += "<form class='quoteNavForm' action='quoteDashboard' method='GET'>" + "<input class='purchaseInput' type='hidden' name='userId' value=" + userId + ">" + "<button class='navbarbtn' type='submit'>Quote Dashboard</button>" + "</form>";
        logoutbtn += "<a class='logoutbtn' href='logout'><i class='fa fa-sign-out'></i></a>";
        profilebtn += "<form action='userProfile'><input value= " +  userId + " name='userId' type='hidden'><button class='profileBtn' type='submit'>Profile</button></form>";
    } else if (Objects.equals(userRole, "agent")) {
        quotebtn += "<a class='navbarbtn' href='agentDashboard.jsp'>Agent Dashboard</a>";
        logoutbtn += "<a class='logoutbtn' href='logout'><i class='fa fa-sign-out'></i></a>";
        profilebtn += "<form action='userProfile'><input value= " +  userId + " name='userId' type='hidden'><button class='profileBtn' type='submit'>Profile</button></form>";
    } else {
        quotebtn += "<a class='navbarbtn' href='quote.jsp'>Get a Quote</a>";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Contact Us</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="images/TaylorIns-inv.png">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div id="maindiv">
    <div id="navbar">
        <a href="index.jsp"><img id="navbarimg" src="images/TaylorIns-inv.png"></a>
        <div id="navbarbuttons">
            <a class="navbarbtn" href="index.jsp">Home</a>
            <%= quotebtn %>
            <a class="navbarbtn" href="about.jsp">About Us</a>
            <a class="navbarbtn" href="contact.jsp">Contact Us</a>
        </div>
        <div id="navbarlogin">
            <%= profilebtn %>
            <%= logoutbtn %>
        </div>
    </div>
    <div class="container" style="max-width: 700px; margin-top: 60px; margin-bottom: 60px;">
        <div class="card shadow-sm border-5">
            <div class="card-body">
                <h2 class="card-title text-center mb-4">Contact Us</h2>
                <form target="_blank" action="https://formsubmit.co/prideout2002@gmail.com" method="POST">
                    <div class="form-group">
                        <div class="form-row row">
                            <div class="col">
                                <input type="text" name="name" class="form-control" placeholder="Full Name" required>
                            </div>
                            <div class="col">
                                <input type="email" name="email" class="form-control" placeholder="Email Address" required>
                            </div>
                        </div>
                    </div>
                    <div class="form-group mt-3">
                        <textarea placeholder="Your Message" class="form-control" name="message" rows="6" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block mt-3">Send Message</button>
                </form>
            </div>
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
<script>
    const userRole = "<%= userRole %>";
    console.log("User role:", userRole);
</script>
</html>
