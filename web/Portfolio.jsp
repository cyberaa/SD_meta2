<%--

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>IdeaBroker</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/STYLEDOCARALHOPARAIDEIAS.css" rel="stylesheet">

</head>
<body>
<style>
    body { background: url(img/background.png); }
</style>

<%@include file="header.jsp" %>

<div class="container">


        <h1>Portfolio</h1>
        <p></p>

    <hr>

   <s:iterator var="idea" value="IdeasList">
        <div class="jumbotron">
            <p></p>
            <div class="media">
                <div class="media-body">
                    <h3 class="media-heading"><s:property value="#idea.title"/></h3>
                    <p><s:property value="#idea.description"/></p>
                    <p></p>
                    <p><h5 class="media-heading">Idea Market Price:</h5> <s:property value="#idea.ideaValue"/></p>
                    <s:iterator var="topic" value="#idea.topics">
                        <a href="ViewTopic.action?topic=<s:property value="#topic"/>">
                            <span class="label label-info">#<s:property value="#topic"/></span>
                        </a>
                    </s:iterator>
                    <!--<span class="label label-primary">caralho</span>
                    <span class="label label-success">SD</span>
                    <span class="label label-info">CACEIRO</span>
                    <span class="label label-warning">CARDOUZO</span>
                    <span class="label label-danger">TOPICSLOLOL</span> -->
                    <p></p>

                    <div class="btn-group">
                        <a href="ViewIdea.action?idea=<s:property value="#idea.title"/>">
                            <button class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-hand-right"></span> View Idea Details
                            </button></a>
                    </div>
                </div>
            </div>
        </div>
    </s:iterator>





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