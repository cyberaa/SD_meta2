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
    <link rel="stylesheet" href="Notifications/notifications.css">
    <!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
</head>
<body>
<div id="fb-root"></div>
<script>
    window.fbAsyncInit = function() {
        FB.init({
            appId      : '436480809808619',
            status     : true, // check login status
            cookie     : true, // enable cookies to allow the server to access the session
            xfbml      : true  // parse XFBML
        });

        // Here we subscribe to the auth.authResponseChange JavaScript event. This event is fired
        // for any authentication related change, such as login, logout or session refresh. This means that
        // whenever someone who was previously logged out tries to log in again, the correct case below
        // will be handled.
        FB.Event.subscribe('auth.authResponseChange', function(response) {
            // Here we specify what we do with the response anytime this event occurs.
            if (response.status === 'connected') {
                // The response object is returned with a status field that lets the app know the current
                // login status of the person. In this case, we're handling the situation where they
                // have logged in to the app.

                //window.location = "/submitIdea.jsp";
                //FB.api('/me', function(response) {
                //    document.loginform.login = response.name;
                //});

            } else if (response.status === 'not_authorized') {
                // In this case, the person is logged into Facebook, but not into the app, so we call
                // FB.login() to prompt them to do so.
                // In real-life usage, you wouldn't want to immediately prompt someone to login
                // like this, for two reasons:
                // (1) JavaScript created popup windows are blocked by most browsers unless they
                // result from direct interaction from people using the app (such as a mouse click)
                // (2) it is a bad experience to be continually prompted to login upon page load.
                FB.login();
            } else {
                // In this case, the person is not logged into Facebook, so we call the login()
                // function to prompt them to do so. Note that at this stage there is no indication
                // of whether they are logged into the app. If they aren't then they'll see the Login
                // dialog right after they log in to Facebook.
                // The same caveats as above apply to the FB.login() call here.
                FB.login();
            }
        });
    };

    // Load the SDK asynchronously
    (function(d){
        var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
        if (d.getElementById(id)) {return;}
        js = d.createElement('script'); js.id = id; js.async = true;
        js.src = "//connect.facebook.net/en_US/all.js";
        ref.parentNode.insertBefore(js, ref);
    }(document));
</script>
<script>(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_GB/all.js#xfbml=1&appId=436480809808619";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
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
    <div class="fb-login-button" data-width="200"  data-height="100" data-max-rows="1" data-show-faces="false" style="display: table; margin-right: auto; margin-left: auto;">Login with Facebook</div>
    <p class="forgot-password"><a href="/Register.jsp">Register</a></p>
</form>
<s:if test="%{tried==true}">
    <div class="isa_error">ERROR. Incorrect password or username.</div>
</s:if>
<s:if test="%{rmierror==true}">
    <div class="isa_error"><b>Failure in connecting with the database. Please try again!</b></div>
</s:if>
</body>
</html>