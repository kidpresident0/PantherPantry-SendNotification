<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login | Food Pantry</title>
    <link rel="stylesheet" href="../includes/styles.css">
    <style>
        .form {
            padding: 20px;
        }
    </style>
</head>
<body>

<div class="form">
    <?php
        require_once("../php_includes/Database.php");
        if(Database::find_code($_GET['email'], $_GET['code'])) {
            Database::code_match($_GET['email'], $_GET['code']);
            echo "<p>Your account has been successfully verified!</p>";
        } else {
            echo "<p>Your account could not be verified. Please contact us for assistance.</p>";
        }
    ?>
    <p id="registerLoginLink"><a href="login.php"><b>Return to login</b></a></p>
</div>

</body>
</html>
