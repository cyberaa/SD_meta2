<%--
  Created by IntelliJ IDEA.
  User: d_cardoso
  Date: 29-11-2013
  Time: 4:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>

<nav class="navbar navbar-inverse " role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-sd-video"></span>&nbsp; IdeaBroker</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">

            <li><a href="<s:url value="submitIdea.jsp" />"><span class="glyphicon glyphicon-plus"></span> &nbsp; Submit Idea</a></li>
            <li><a href="<s:url action="GetPortfolio"/>"><span class="glyphicon glyphicon-user"></span> &nbsp; Portfolio</a></li>
            <li><a href="<s:url action="ViewTransaction" />"><span class="glyphicon glyphicon-resize-full"></span>&nbsp; Transactions</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-star"></span>&nbsp; Watchlist</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-book"></span>&nbsp; Hall of Fame</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Search by <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a onclick="javascript:document.getElementById('SearchOption').value='Ideas';javascript:document.getElementById('search').placeholder='Search by Idea'">Ideas</a></li>
                    <li><a onclick="javascript:document.getElementById('SearchOption').value='Topics';javascript:document.getElementById('search').placeholder='Search by Topic'">Topics</a></li>
                </ul>
            </li>
        </ul>


        <form method="post" action ="search" class="navbar-form navbar-left" role="search">
            <div class="form-group">
                <input type="text" id = "search" name ="search" class="form-control" placeholder="Search by Idea">
            </div>
            <input type="text"  id = "SearchOption" style="display: none" name = "SearchOption" value="Ideas"/>">
            <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
        </form>

        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-usd"></span><s:property value='#session.client.DEICoinsString' /></a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><s:property value='#session.client.userName' /><b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="#">Logout</a></li>
                </ul>
            </li>
        </ul>
    </div><!-- /.navbar-collapse -->
</nav>