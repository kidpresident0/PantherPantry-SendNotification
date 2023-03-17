<?php
    session_start();
?>
<!doctype HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login | Food Pantry</title>
    <link rel="stylesheet" href="../includes/styles.css">
</head>
<body>
    <div id="loginResult">
        
        <button id="returnLink"><a href="login.php">Return to Login</a></button>
        
        <?php
            require_once("../php_includes/User.php");
            require_once("../php_includes/Database.php");
            // login.php is where the user inputs their login!
            define('USERNAME', $_POST['username']);
            define('PASSWORD', $_POST['password']);
        
            // Function for detecting errors.
            function detectErrors() { // Function for detecting errors.
                $errorDetected = 
                    $errorNullUsername = 
                    $errorNullPassword = false;
                
                $username = $_POST['username'];
                $password = $_POST['password'];
                
                // Errors for null value.
                if (null == (USERNAME)) {
                    $errorDetected = true;
                    $errorNullUsername = true;
                }
                if (null == (PASSWORD)) {
                    $errorDetected = true;
                    $errorNullPassword = true;
                }

                // This section shows error messages to user.
                if ($errorDetected == true) {
                    echo "<style>p {color: red}</style>";
                    echo "<h1>Error!</h1>\n";
                    if ($errorNullUsername == true) {
                        echo "<p>Please type in a username or an email address.</p>\n";
                        }
                    if ($errorNullPassword == true) {
                        echo "<p>Please type in a Password.</p>\n";
                        }
                    exit;
                } else { // When NULL errors aren't detected, searches for user in the database.
                        if (Database::pass_match($username, $password)) {
                            $_SESSION['logged_in_userID'] = Database::find_user($username)[0]->userID;
                            $_SESSION['logged_in_username'] = Database::find_user($username)[0]->username;
                            $_SESSION['logged_in_userEmail'] = Database::find_user($username)[0]->userEmail;
                            $_SESSION['logged_in_firstName'] = Database::find_user($username)[0]->firstName;
                            $_SESSION['logged_in_lastName'] = Database::find_user($username)[0]->lastName;
                            $_SESSION['logged_in_userRole'] = Database::find_user($username)[0]->userRole;
                            $_SESSION['logged_in_phoneNumber'] = Database::find_user($username)[0]->phoneNumber;

                            // use to test if variables work
                            //echo $_SESSION['logged_in_username'] . $_SESSION['logged_in_userEmail'] . $_SESSION['logged_in_firstName'] . $_SESSION['logged_in_lastName'] . $_SESSION['logged_in_userRole'];

                            echo "<p>Login succeeded!</p>\n";
                            echo "<h1>Welcome, " . Database::find_user($username)[0]->firstName . "!</h1>";
                            echo "<button type=\"button\" onclick=\"window.location.href='settings.php'\" style='margin-left: 100px;'>Go to settings</button>";
                    } else {
                        echo "<h1>Login failed!</h1>\n";
                        echo "<p>Please re-enter your login information.</p>";
                    }
                }
            }

            detectErrors(); // calls function
        
        ?>
    </div>
</body>
</html>