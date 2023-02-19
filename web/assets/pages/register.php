<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Account Creation | Food Pantry</title>
    <link rel="stylesheet" href="../includes/styles.css">
</head>
<body>
    <form id="loginForm" class="form" method="post" action="register_account.php">
        <div id="formContents">
            
            <p>Please create an account.</p>
            
            <div id="formFields">
                <label>First Name:</label>
                <input type="text" id="fName" name="fName" placeholder="Enter your first name..."><br>
                
                <label>Last Name:</label>
                <input type="text" id="lName" name="lName" placeholder="Enter your last name..."><br>
                
                <label>Email:</label>
                <input type="text" id="email" name="email" placeholder="Enter an email address..."><br>
                
                <label>Username:</label>
                <input type="text" id="username" name="username" placeholder="Enter a username..."><br>
                
                <label>Password:</label>
                <input type="password" id="password" name="password" placeholder="Enter a password..."><br>
                
                <label>Confirm Password:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Re-type your password..."><br>
            </div>
            
            <input type="submit" id="registerButton" value="REGISTER!">
            
            <p id="registerLoginLink">Already have an account? <a href="login.php"><b>Login here!</b></b></a></p>
    
        </div>
    </form>
</body>
</html>
<?php
