<!doctype HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Account Creation | Food Pantry</title>
    <link rel="stylesheet" href="../includes/styles.css">
</head>
<body>
    <div id="registerResult">
    
        <button id="returnLink"><a href="register.php">Return to Registration</a></button>
        
        <?php
            require_once("../php_includes/Database.php");
            require_once("../php_includes/Mail.php");
            define('FNAME', $_POST['fName']);
            define('LNAME', $_POST['lName']);
            define('EMAIL', $_POST['email']);
            define('USERNAME', $_POST['username']);
            define('PASSWORD', $_POST['password']);
            define('CPASSWORD', $_POST['confirmPassword']);

            // Function for detecting errors
            function detectErrors() {
                $errorDetected = 
                    $errorNullName =
                    $errorNullEmail =
                    $errorNullUsername = 
                    $errorNullPassword = 
                    $errorNullCPassword = 
                    $errorInvalidName = 
                    $errorInvalidEmail = 
                    $errorUserExists = 
                    $errorPassExists =
                    $errorEmailExists =
                    $errorPasswordMatch = false;
                
                $email = $_POST['email'];
                $username = $_POST['username'];
                $password = $_POST['password'];
                
                // Errors for null value.
                if (null == (FNAME) || null == (LNAME)) {
                    $errorDetected = true;
                    $errorNullName = true;
                }
                if (null == (EMAIL)) {
                    $errorDetected = true;
                    $errorNullEmail = true;
                }
                if (null == (USERNAME)) {
                    $errorDetected = true;
                    $errorNullUsername = true;
                }
                if (null == (PASSWORD)) {
                    $errorDetected = true;
                    $errorNullPassword = true;
                }
                if ((null == (CPASSWORD)) && ($errorNullPassword == false)) {
                    $errorDetected = true;
                    $errorNullCPassword = true;
                }
                
                // Errors for invalid values.
                // When Name contains non-alphabetical characters
                if (((ctype_alpha(FNAME) == false) || (ctype_alpha(LNAME)) == false) && ($errorNullName == false)) {
                    $errorDetected = true;
                    $errorInvalidName = true;
                }
                // When Email isn't a valid address
                if ((filter_var(EMAIL, FILTER_VALIDATE_EMAIL) == false) && ($errorNullEmail == false)) {
                    $errorDetected = true;
                    $errorInvalidEmail = true;
                } 

                // Errors for already existing values.
                if (Database::does_email_exist($email)) {
                    $errorDetected = true;
                    $errorEmailExists = true;
                }
                if (Database::does_user_exist($username)) {
                    $errorDetected = true;
                    $errorUserExists = true;
                }
                if (Database::does_pass_exist($password)) {
                    $errorDetected = true;
                    $errorPassExists = true;
                }
                
                // Error for passwords not matching.
                if ((PASSWORD != CPASSWORD) && ($errorNullPassword == false) && ($errorNullCPassword == false)) {
                    $errorDetected = true;
                    $errorPasswordMatch = true;
                }
                
                if ($errorDetected == true) {
                    echo "<style>p {color: red}</style>";
                    echo "<h1>Error!</h1>\n";
                    if ($errorNullName == true) {
                        echo "<p>Please type in a name.</p>\n";
                    }
                    if ($errorNullEmail == true) {
                        echo "<p>Please type in an email address</p>\n";
                    }
                    if ($errorNullUsername == true) {
                        echo "<p>Please type in a username.</p>\n";
                    }
                    if ($errorNullPassword == true) {
                        echo "<p>Please type in a password.</p>\n";
                    }
                    if ($errorNullCPassword == true) {
                        echo "<p>Please re-type your password.</p>\n";
                    }
                    if ($errorInvalidName == true) {
                        echo "<p>Name cannot contain numbers or other symbols.</p>\n";
                    }
                    if ($errorInvalidEmail == true) {
                        echo "<p>Please enter a valid email address.</p>\n";
                    }
                    if ($errorUserExists == true || $errorPassExists == true || $errorEmailExists == true) {
                        echo "<p>Account creation failed!</p>";
                    }
                    if ($errorPasswordMatch == true) {
                        echo "<p>Passwords did not match! Please re-enter your password.</p>\n";
                    }
                    exit;
                }
                createUser();
            }
        
            // Function for creating a user account
            function createUser() {
                require_once("../php_includes/Database.php");
                $username = $_POST['username'];
                $fName = $_POST['fName'];
                $lName = $_POST['lName'];
                $email = $_POST['email'];
                $password = $_POST['password'];
                
                $password = password_hash($password, PASSWORD_BCRYPT);
                
                Database::create_user($username, $fName, $lName, $email, $password);
                echo "<p>Account created successfully!</p>\n";
                echo "<h1>Welcome, " . $fName . "!</h1>";
                Mail::send_verify($email);
            }
        
            detectErrors(); // calls function
        ?>
    </div>
</body>
</html>