<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    Long userId = (Long) session.getAttribute("userId");

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
    <title>About</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="images/TaylorIns-inv.png">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
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
            <a class="navbarbtn" href="index.jsp">About Us</a>
        </div>
        <div id="navbarlogin">
            <a href="login.jsp"><img id="profileimg" src="images/profile.jpg"></a>
        </div>
    </div>
    <br>
    <div class="container p-4">
        <h2 class="text-center">My Father's Struggles:</h2>
        <br>
        <div class="row align-items-center">
            <div class="col-md-8">
                <p class="fs-5">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus congue sollicitudin vestibulum. Donec sem massa, bibendum eu fermentum id, condimentum non felis. Nullam cursus ipsum nec magna bibendum volutpat eget sed augue. Proin auctor ultricies ex nec dignissim. Etiam tincidunt cursus tortor, quis luctus enim sagittis in. Nulla eget pulvinar nulla. Integer suscipit augue ut lorem ultrices tristique. Praesent metus ex, dapibus et urna et, ultrices faucibus lectus. Nunc dapibus, quam sit amet mattis pulvinar, eros purus consequat felis, eu hendrerit leo augue vel massa. Sed sagittis nulla eu lacinia feugiat. Nam faucibus dui vitae velit tempor, facilisis pulvinar neque dictum. Proin sit amet pharetra dui, id dictum elit. Vestibulum ultricies sagittis diam, eget sagittis metus bibendum a. Fusce imperdiet, ipsum nec vestibulum vestibulum, elit nunc tempor purus, eget blandit tellus felis et nunc. Aliquam porta lorem velit. Aenean sollicitudin dui sapien. Aliquam sed fermentum mi. In malesuada tempor tempus. Fusce pulvinar sapien et purus auctor molestie. Suspendisse at tristique augue.</p>
            </div>
            <div class="col-md-4 text-center">
                <img src="images/JoshTaylorSr.jpg" alt="Fisherman" class="img-fluid rounded border border-dark">
                <h5 class="mt-2">Conche 1970: Josh Taylor Senior is laid off from the local Asbestos mine.</h5>
            </div>
        </div>
    </div>
    <div class="container p-4">
        <h2 class="text-center">My Troubled Youth:</h2>
        <br>
        <div class="row align-items-center">
            <div class="col-md-4 text-center">
                <img src="images/PeasantJosh.jpg" alt="Peasant" class="img-fluid rounded border border-dark">
                <h5 class="mt-2">Conche 1971: A young Josh Taylor begs for food from bypassing strangers.</h5>
            </div>
            <div class="col-md-8">
                <p class="fs-5">Nulla facilisi. Vivamus risus mi, cursus et felis ac, interdum dictum lorem. Etiam vulputate ante eget hendrerit pellentesque. Aliquam et blandit sem, vitae rhoncus risus. In nunc felis, pulvinar id lobortis vitae, finibus id nunc. Quisque posuere lacinia nisi. Morbi faucibus, nulla a vestibulum sagittis, turpis leo tincidunt felis, sit amet maximus lacus est et eros. Praesent sed leo magna. Donec et tempus mi, congue interdum lectus. Nunc felis nunc, semper ac nisi ac, tincidunt tristique dui. Ut feugiat at sapien eu consectetur. Phasellus rutrum a nisi sit amet suscipit. Suspendisse vel arcu suscipit, gravida risus sit amet, pellentesque arcu. Suspendisse lectus enim, lacinia vitae hendrerit eget, congue ac lorem. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam gravida sodales massa, id facilisis ligula lobortis vel. Suspendisse nibh est, euismod id mauris ac, imperdiet suscipit elit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Pellentesque nec orci sit amet neque pulvinar volutpat nec vitae sem. Nullam interdum sit amet velit quis elementum. Integer sagittis semper mi volutpat sodales.</p>
            </div>
        </div>
    </div>
    <div class="container p-4">
        <h2 class="text-center">My Experimental Years:</h2>
        <br>
        <div class="row align-items-center">
            <div class="col-md-8">
                <p class="fs-5">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus congue sollicitudin vestibulum. Donec sem massa, bibendum eu fermentum id, condimentum non felis. Nullam cursus ipsum nec magna bibendum volutpat eget sed augue. Proin auctor ultricies ex nec dignissim. Etiam tincidunt cursus tortor, quis luctus enim sagittis in. Nulla eget pulvinar nulla. Integer suscipit augue ut lorem ultrices tristique. Praesent metus ex, dapibus et urna et, ultrices faucibus lectus. Nunc dapibus, quam sit amet mattis pulvinar, eros purus consequat felis, eu hendrerit leo augue vel massa. Sed sagittis nulla eu lacinia feugiat. Nam faucibus dui vitae velit tempor, facilisis pulvinar neque dictum. Proin sit amet pharetra dui, id dictum elit. Vestibulum ultricies sagittis diam, eget sagittis metus bibendum a. Fusce imperdiet, ipsum nec vestibulum vestibulum, elit nunc tempor purus, eget blandit tellus felis et nunc. Aliquam porta lorem velit. Aenean sollicitudin dui sapien. Aliquam sed fermentum mi. In malesuada tempor tempus. Fusce pulvinar sapien et purus auctor molestie. Suspendisse at tristique augue.</p>
            </div>
            <div class="col-md-4 text-center">
                <img src="images/NerdJosh.jpg" alt="Fisherman" class="img-fluid rounded border border-dark">
                <h5 class="mt-2">St. John's 1990: A early 20's Josh Taylor struggles to find his place in a progressing society.</h5>
            </div>
        </div>
    </div>
    <div class="container p-4">
        <h2 class="text-center">My Epiphany:</h2>
        <br>
        <div class="row align-items-center">
            <div class="col-md-4 text-center">
                <img src="images/FishermanJosh.jpg" alt="Peasant" class="img-fluid rounded border border-dark">
                <h5 class="mt-2">Conche 1999: A 30-year-old Josh Taylor sporting his favourite fishing wear.</h5>
            </div>
            <div class="col-md-8">
                <p class="fs-5">Nulla facilisi. Vivamus risus mi, cursus et felis ac, interdum dictum lorem. Etiam vulputate ante eget hendrerit pellentesque. Aliquam et blandit sem, vitae rhoncus risus. In nunc felis, pulvinar id lobortis vitae, finibus id nunc. Quisque posuere lacinia nisi. Morbi faucibus, nulla a vestibulum sagittis, turpis leo tincidunt felis, sit amet maximus lacus est et eros. Praesent sed leo magna. Donec et tempus mi, congue interdum lectus. Nunc felis nunc, semper ac nisi ac, tincidunt tristique dui. Ut feugiat at sapien eu consectetur. Phasellus rutrum a nisi sit amet suscipit. Suspendisse vel arcu suscipit, gravida risus sit amet, pellentesque arcu. Suspendisse lectus enim, lacinia vitae hendrerit eget, congue ac lorem. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam gravida sodales massa, id facilisis ligula lobortis vel. Suspendisse nibh est, euismod id mauris ac, imperdiet suscipit elit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Pellentesque nec orci sit amet neque pulvinar volutpat nec vitae sem. Nullam interdum sit amet velit quis elementum. Integer sagittis semper mi volutpat sodales.</p>
            </div>
        </div>
    </div>
    <div class="container p-4">
        <h2 class="text-center">Creation of Taylor's Insurance and my journey in insurance equity:</h2>
        <br>
        <div class="row align-items-center">
            <div class="col-md-8">
                <p class="fs-5">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus congue sollicitudin vestibulum. Donec sem massa, bibendum eu fermentum id, condimentum non felis. Nullam cursus ipsum nec magna bibendum volutpat eget sed augue. Proin auctor ultricies ex nec dignissim. Etiam tincidunt cursus tortor, quis luctus enim sagittis in. Nulla eget pulvinar nulla. Integer suscipit augue ut lorem ultrices tristique. Praesent metus ex, dapibus et urna et, ultrices faucibus lectus. Nunc dapibus, quam sit amet mattis pulvinar, eros purus consequat felis, eu hendrerit leo augue vel massa. Sed sagittis nulla eu lacinia feugiat. Nam faucibus dui vitae velit tempor, facilisis pulvinar neque dictum. Proin sit amet pharetra dui, id dictum elit. Vestibulum ultricies sagittis diam, eget sagittis metus bibendum a. Fusce imperdiet, ipsum nec vestibulum vestibulum, elit nunc tempor purus, eget blandit tellus felis et nunc. Aliquam porta lorem velit. Aenean sollicitudin dui sapien. Aliquam sed fermentum mi. In malesuada tempor tempus. Fusce pulvinar sapien et purus auctor molestie. Suspendisse at tristique augue.</p>
            </div>
            <div class="col-md-4 text-center">
                <img src="images/ProtestJosh.jpg" alt="Fisherman" class="img-fluid rounded border border-dark">
                <h5 class="mt-2">St. John's 2024: Present day Josh Taylor supporting all the right things.</h5>
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
</html>
