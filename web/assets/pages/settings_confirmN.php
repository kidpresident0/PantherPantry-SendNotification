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
        <button id="returnLink"><a href="settings.php">Return to Settings</a></button>
        <h1>Notification Settings</h1>
        <?php
        require_once("../php_includes/Database.php");
        define('N_EMAIL', $_POST['message1']);
        define('N_SMS', $_POST['message2']);

        function setNotifs($n_email, $n_sms) {
            $receive = "";
            $type = "";
            if ($n_email == "true") {
                $receive = "Yes";
                if ($n_sms == "true") {
                    $type = "Both";
                    echo "<p>Your notifications have been set to Email and SMS!</p>";
                } else { // $n_sms returns nothing
                    $type = "Email";
                    echo "<p>Your notifications have been set to Email!</p>";
                }
            } else if ($n_sms == "true") { // and $n_email returns nothing
                $receive = "Yes";
                $type = "SMS";
                echo "<p>Your notifications have been set to SMS!</p>";
            } else { // neither are true
                $receive = "No";
                $type = NULL;
                echo "<p>Your notifications have been unset.</p>";
            }

            Database::set_notifs($_SESSION['logged_in_userID'], $receive, $type);
        }

        setNotifs(N_EMAIL, N_SMS);

        ?>
    </div>
</body>
</html>
