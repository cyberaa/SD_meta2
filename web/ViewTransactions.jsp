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


        <h1>User Transactions:</h1>
        <p></p>

    <hr>


        <div class="jumbotron">
            <p></p>
            <div class="media">
                <div class="media-body">
                    <textarea name = "transactionsList" rows="10" id = "topicsList" style = "resize: none; width: 100%;" readonly="true"><s:iterator var="transaction" value="Transac"><s:property value="transaction"/></s:iterator></textarea>
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