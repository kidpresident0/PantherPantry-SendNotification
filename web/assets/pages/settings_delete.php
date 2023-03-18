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
        p {
            margin: 0 35px;
        }
    </style>
</head>
<body>

<form id="notifsForm" class="form" method="post" action="settings_confirmD.php">
    <div id="formContents">

        <h1>Account Deletion</h1>

            <p>Warning!<br>Once you delete your account, you cannot recover it. If you are sure you want to delete, please click the "Delete!" button below.</p>

        <button type="button" id="returnLink" onclick="window.location.href='settings.php'">Back</button>
        <input type="submit" id="notifsSubmit" value="Delete!">
    </div>
</form>
</body>
</html>
<?php
