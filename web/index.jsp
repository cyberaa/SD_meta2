<%--
  Created by IntelliJ IDEA.
  User: d_cardoso
  Date: 23-11-2013
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>



<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="en"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>IdeaBroker</title>
    <link rel="stylesheet" href="css/style.css">
    <!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>
<body>
<table id="wrapper">
    <tr>
        <td><img src="img/logo.png" alt="" /></td>
    </tr>
</table>
<form method="post" action="HelloWorld" class="login">
    <p>
        <s:textfield name="userName" id="login" label="Username"/>
    </p>
    <p>
        <s:password name="password" id="password" label="Password"/>
    </p>
    <p class="login-submit">
        <button type="submit" class="login-button">Login</button>
    </p>
    <p class="forgot-password"><a href="http://www.google.com">Register</a></p>
</form>

</body>
</html>