<?php
session_start();
require_once("../php_includes/Database.php");
?>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Settings | Food Pantry</title>
    <link rel="stylesheet" href="../includes/styles.css">
    <style>
        #formFields {
            text-align: left;
            margin: 10px 75px;
        }
        .checkbox {
            margin-left: 185px;
        }
        #notifsForm p {
            font-size: 14px;
            text-align: left;
            margin-left: 75px;
        }
    </style>
</head>
<body>

<form id="notifsForm" class="form" method="post" action="settings_confirmN.php">
    <div id="formContents">

        <h1>Notification Settings</h1>

        <div id="formFields">

            <label>Send notifications through...</label><br>
            <input type="hidden" id="message1" name="message1" value="false">
            <input type="checkbox" id="message1" name="message1" class="checkbox" value="true" <?php
                if ((Database::find_notifs($_SESSION['logged_in_userID'])[0]['notificationType'] == "Email") || (Database::find_notifs($_SESSION['logged_in_userID'])[0]['notificationType'] == "Both")) {
                    echo 'checked';
                }
            ?>
            >
            <label for="message1">Email</label><br>
            <input type="hidden" id="message2" name="message2" value="false">
            <input type="checkbox" id="message2" name="message2" class="checkbox" value="true" <?php
            if ((Database::find_notifs($_SESSION['logged_in_userID'])[0]['notificationType'] == "SMS") || (Database::find_notifs($_SESSION['logged_in_userID'])[0]['notificationType'] == "Both")) {
                echo 'checked';
            }
            ?>
            >
            <label for="message2">SMS (Text Message)</label><br>

        </div>

        <p>Email notifications will be sent to... <b><?php echo $_SESSION['logged_in_userEmail']; ?></b></p>
        <p>SMS notifications will be sent to... <b> <?php
                                                    if ($_SESSION['logged_in_phoneNumber'] != "") {
                                                        echo $_SESSION['logged_in_phoneNumber'];
                                                    } else { echo "[Not Set]"; }
                                                    ?></b></p>

        <button type="button" id="returnLink" onclick="window.location.href='settings.php'">Back</button>
        <input type="submit" id="notifsSubmit" value="Submit">

    </div>
</form>
</body>
</html>
