RMIerror.jsp<%--
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
    <link rel="stylesheet" href="Notifications/notifications.css">
    <!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>
<body>
<div id="fb-root"></div>
<script>
    (function (d) {
        var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
        if (d.getElementById(id)) { return; }
        js = d.createElement('script'); js.id = id; js.async = true;
        js.src="//connect.facebook.net/en_US/all.js";
        ref.parentNode.insertBefore(js, ref);
    }(document));


    window.fbAsyncInit = function() {
        FB.init({
            appId: 436480809808619,
            channelUrl: '//' + window.location.hostname + '/channel',
            status: true,
            cookie: true,
            xfbml: true
        })

        FB.Event.subscribe('auth.authResponseChange', function(response) {
            document.getElementById('token').value = response.authResponse.accessToken;
        })
    }


    function Login() {
        FB.login(function(response) {
            if(response.authResponse) {
                FB.api('/me', function(response) {
                    alert(response.id);
                });
            }
        });
    }
</script>

<table id="wrapper" class=center>
    <tr>
        <td><img src="img/logo.png" alt="" align="center"/></td>
    </tr>
</table>
<form method="post" action="login" class="login" id="loginform">
    <p>
        <s:textfield name="userName" id="login" label="Username"/>
    </p>
    <p>
        <s:password name="password" id="password" label="Password"/>
    </p>
    <p class="login-submit">
        <button type="submit" class="login-button">Login</button>
    </p>


    <p class="forgot-password"><a href="/Register.jsp">Register</a></p>
</form>



<form method="post" action="FacebookLogin" class="login" id="loginformfacebook">
    <div id="auth-status">
        <div id="auth-loggedout">
            <div class="fb-login-button" autologoutlink="true" scope="email,user_checkins,publish_actions,publish_stream,read_stream">Login  with Facebook</div>
        </div>

        <div id="auth-loggedin" style="display: none"></div>
    </div>

    <input type="hidden" name="token" value="" id="token" >

    <button type="submit" class="login-button-facebook">Login With Facebook</button>
</form>

<s:if test="%{tried==true}">
    <div class="isa_error">ERROR. Incorrect password or username.</div>
</s:if>
<s:if test="%{rmierror==true}">
    <div class="isa_error"><b>Failure in connecting with the database. Please try again!</b></div>
</s:if>
</body>
</html>