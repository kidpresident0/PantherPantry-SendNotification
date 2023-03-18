<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Settings | Food Pantry</title>
    <link rel="stylesheet" href="../includes/styles.css">
    <style>
        ul {
            margin: 30px 70px;
            padding-inline-start: 0;
        }
        li {
            border: rgba(0,0,0,0) 1px solid;
            border-radius: 5px;
            padding: 5px;
            list-style-type: none;
            text-align: left;
        }
        a {
            color: black;
            text-decoration: none;
            font-weight: bold;
        }
        li:hover {
            background-color: rgb(128, 128, 128, 0.5);
            border: rgb(128, 128, 128, 0.5) 1px solid;
            border-radius: 5px;
        }
        li:hover a {
            color: white;
        }
    </style>
</head>
<body>
    <div class="form">
        <h1>Settings</h1>
        <ul>
            <a href="settings_profile.php"><li>Profile</li></a>
            <a href="settings_notifs.php"><li>Notifications</li></a>
            <a href="settings_delete.php"><li>Delete Account</li></a>
        </ul>
    </div>
</body>
</html>
<?php
