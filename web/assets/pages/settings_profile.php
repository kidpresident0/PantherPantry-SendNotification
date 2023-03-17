<?php
session_start();
?>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Settings | Food Pantry</title>
    <link rel="stylesheet" href="../includes/styles.css">
    <style>
        label, input {
            margin: 5px 0;
        }
    </style>
</head>
<body>

<form id="notifsForm" class="form" method="post" action="settings_confirm.php">
    <div id="formContents">

        <h1>Profile Settings</h1>
        <p>Change your information.</p>

        <div id="formFields">
            <?php
            echo "<label>Username:</label>
                    <input type='text' id='username' name='username' value='" . $_SESSION['logged_in_username'] . "'><br>";
            echo "<label>First Name:</label>
                    <input type='text' id='fName' name='fName' value='" . $_SESSION['logged_in_firstName'] . "'><br>";
            echo "<label>Last Name:</label>
                    <input type='text' id='lName' name='lName' value='" . $_SESSION['logged_in_lastName'] . "'><br>";
            echo "<label>Email Address:</label>
                    <input type='text' id='email' name='email' value='" . $_SESSION['logged_in_userEmail'] . "'><br>";
            echo "<label>Phone Number:</label>
                    <input type='text' id='pNumber' name='pNumber' value='" . $_SESSION['logged_in_phoneNumber'] . "'><br>";
            echo "<label>Password:</label>
                    <input type='password' id='password' name='password' placeholder='Enter a password...'><br>";
            echo "<label>Re-Confirm Password:</label>
                    <input type='password' id='confirmPassword' name='confirmPassword' placeholder='Re-type your password...'>";
            ?>
        </div>

        <button type="button" id="returnLink" onclick="window.location.href='settings.php'">Back</button>
        <input type="submit" id="notifsSubmit" value="Submit">

    </div>
</form>
</body>
</html>
