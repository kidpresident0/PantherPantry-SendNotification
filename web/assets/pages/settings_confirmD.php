<?php
session_start();
?>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Settings | Food Pantry</title>
    <link rel="stylesheet" href="../includes/styles.css">
</head>
<body>
<div id="settingsResult">
    <button id="returnLink"><a href="login.php">Return to Login</a></button>
    <h1>Notification Settings</h1>
    <?php
    require_once("../php_includes/Database.php");
    Database::delete_user($_SESSION['logged_in_userID']);
    ?>
    <p>Your account has been deleted.</p>
</div>
</body>
</html>
