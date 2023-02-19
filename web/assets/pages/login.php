<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login | Food Pantry</title>
    <link rel="stylesheet" href="../includes/styles.css">
</head>
<body>

    <form id="loginForm" class="form" method="post" action="login_user.php">
        <div id="formContents">
            
            <p>Please login into your Food Pantry account.</p>
            
            <div id="formFields">
                
                <label>Username/Email:</label>
                <input type="text" id="username" name="username" placeholder="Enter a username or email..."><br>
                
                <label>Password:</label>
                <input type="password" id="password" name="password" placeholder="Enter a password..."><br>
            </div>
            
            <input type="submit" id="loginButton" value="LOGIN!">
            
            <p id="registerLoginLink">Don't have an account? <a href="register.php"><b>Register here!</b></a></p>
        </div>
    </form>
</body>
</html>
