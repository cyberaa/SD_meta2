<%--

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <title>IdeaBroker</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/submitStyle.css" rel="stylesheet">

</head>
<body>
<!--<s:if test="%{client==null}">
    <script>
        window.location = "/index.jsp";
    </script>
</s:if>   -->

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
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><s:property value="userName" /><b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="#">Logout</a></li>
                </ul>
            </li>
        </ul>
    </div><!-- /.navbar-collapse -->
</nav>



<!-- Main container -->
<div class="" style="padding-left: 100px; padding-right: 100px;">

    <!-- Submit new idea panel -->
    <div class="panel panel-default"  >

        <div class="panel-heading">
            <h3 class="panel-title">
                <i class="fa fa-flash" style="margin-right: 3px;"></i>
                What's your idea?
            </h3>
        </div>

        <p></p>

        <div class="panel-body">

            <div class="form-group">
                <label for="titleIdea1">Title</label>
                <input type="title" class="form-control-title" id="ideaTitle" placeholder="Enter title of idea">
            </div>

            <label for="titleIdea1">Description</label>
            <textarea class="form-control" rows="3"></textarea>

            <p></p>


            <div class="col-lg-6" >
                <div class="input-group">
                    <input type="text" class="form-control" style="margin-left:0px;">
            <span class="input-group-btn">
              <button class="btn btn-default" type="button">Add Topic</button>
            </span>
                </div><!-- /input-group -->

                <p></p>

                <textarea class="form-control" rows="3"></textarea>
            </div><!-- /.col-lg-6 -->

            <div class="row" style="margin-top: 20px;">
                <div class="col-md-2">
                    <div class="form-group">
                        <label class="control-label" for="investment">Price per Share</label>
                        <input type="text" class="form-control" id="investment">
                    </div>
                </div>

                <!-- Attach File -->
                <div class="col-md-5">
                    <div class="form-group">
                        <label for="attach">Attach file</label>
                        <input type="file" id="attach">
                        <p class="help-block">Only images</p>
                    </div>
                </div>
            </div>
        </div>

        <button type="button" style="float:right;" class="btn btn-default navbar-btn">Submit</button>
    </div>

    <!-- Footer -->
    <div id="push"></div>
    <div id="footer">
        <p class="muted credit">Powered by: <a href="https://www.facebook.com/bruno.caceiro">Bruno Caceiro</a>&
            <a href="https://www.facebook.com/david.p.cardoso">David Cardoso</a>.</p>
    </div>





<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://code.jquery.com/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>
