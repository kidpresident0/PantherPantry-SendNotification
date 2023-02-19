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
                            echo "<p>Login succeeded!</p>\n";
                            echo "<h1>Welcome, " . Database::find_user($username)[0]["firstName"] . "!</h1>";
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