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

<nav class="navbar navbar-inverse " role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">IdeaBroker</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Submit Idea</a></li>
            <li><a href="#">My Ideas</a></li>
            <li><a href="#">Transactions</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Search by <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="#">Ideas</a></li>
                    <li><a href="#">Topics</a></li>
                </ul>
            </li>
        </ul>


        <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Search">
            </div>
            <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
        </form>

        <ul class="nav navbar-nav navbar-right">
            <li><a href="#">DEIcoins:10000</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">USERNAME <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="#">Logout</a></li>
                </ul>
            </li>
        </ul>
    </div><!-- /.navbar-collapse -->
</nav>



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