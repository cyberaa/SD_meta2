<%--

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>Dark Login Form</title>

    <link rel="stylesheet" href="css/registerStyle.css">

    <!--[if lt IE 9]>
        <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>
    <form method="post" action="index.html" class="login">

        <!-- Username Info -->
        <p>
            <label for="username">Username:</label>
            <input type="text" name="username" id="username" value="">
        </p>

        <!-- Password -->
        <p>
            <label for="password">Password:</label>
            <input type="password" name="password" id="password" value="">
        </p>

        <!-- Name -->
        <p>
            <label for="name">Name:</label>
            <input type="text" name="name" id="name" value="">
        </p>

        <!-- Lastname -->
        <p>
            <label for="lastname">Lastname:</label>
            <input type="text" name="lastname" id="lastname" value="">
        </p>

        <!-- Email -->
        <p>
            <label for="email">E-mail:</label>
            <input type="text" name="email" id="email" value="">
        </p>

        <!-- Submit Button -->
        <p class="login-submit">
            <button type="submit" class="login-button">Login</button>
        </p>

    </form>

</body>
</html>