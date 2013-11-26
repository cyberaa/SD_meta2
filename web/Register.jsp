<%--

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>


<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Dark Login Form</title>
    <link rel="stylesheet" href="css/registerStyle.css">
    <!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>

<body>


<table id="wrapper" class=center>
    <tr>
        <td><img src="img/logo.png" alt="" align="center"/></td>
    </tr>
</table>


<form method="post" action="Register" class="login">

    <p>
        <s:textfield name = "username" id = "login" label = "Username"/>
    </p>

    <p>
        <s:password name=  "password" id = "password" label = "Password"/>
    </p>

    <p>
        <s:textfield name = "name" id = "name" label = "Name"/>
    </p>

    <p>
        <s:textfield name = "lastname" id = "lastname" label = "Lastname"/>
    </p>

    <p>
        <s:textfield name = "email" id = "email" label = "E-mail"/>
    </p>

    <p class="login-submit">
        <button type="submit" class="login-button">Register</button>
    </p>

</form>


</body>
</html>