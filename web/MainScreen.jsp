<%--

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
    <title>IdeaBroker</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/mainPageStyle.css" rel="stylesheet">

</head>
<body>
<style>
    body { background: url(img/background.png); }
</style>



<div class="container">

    <div class="hero-unit hidden-phone">
        <h1>Welcome to IdeaBroker</h1>
        <p>We do amazing things that will make you want to give us money. Lots of
            money.</p>
        <p></p>
    </div>

    <hr>

    <div class="row main-features">
        <div class="span3">
            <div class="well">
                <h3>Idea1</h3>
                <p>Enter a brief description of why it's so awesome here. Then move on to
                    the next feature.</p>
            </div>
        </div>
        <hr>
        <div class="span3">
            <div class="well">
                <h3>Idea2</h3>
                <p>Enter a brief description of why it's so awesome here. Then move on to
                    the next feature.</p>
            </div>
        </div>

        <hr>

        <div class="span3">
            <div class="well">
                <h3>Idea3</h3>
                <p>Enter a brief description of why it's so awesome here. Then move on to
                    the next feature.</p>
            </div>
        </div>
    </div>
</div>

<div id="push"></div>

<div id="footer">
    <div class="container">
        <p>Powered by: <a href="https://www.facebook.com/bruno.caceiro">Bruno Caceiro</a> & <a href="https://www.facebook.com/david.p.cardoso">David Cardoso</a>.</p>
    </div>
</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://code.jquery.com/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>