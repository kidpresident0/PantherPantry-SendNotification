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
        <h1>Profile Settings</h1>
        <?php
            require_once("../php_includes/Database.php");
            define('FNAME', $_POST['fName']);
            define('LNAME', $_POST['lName']);
            define('EMAIL', $_POST['email']);
            define('USERNAME', $_POST['username']);
            define('PASSWORD', $_POST['password']);
            define('CPASSWORD', $_POST['confirmPassword']);
            define('PNUMBER', $_POST['pNumber']);

            // Function for detecting errors
            function detectErrors() {
                $errorDetected =
                $errorNullName =
                $errorNullEmail =
                $errorNullUsername =
                $errorNullCPassword =
                $errorInvalidName =
                $errorInvalidEmail =
                $errorInvalidPNumber =
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
                if ((null == (CPASSWORD)) && (null !=(PASSWORD))) {
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
                if (PNUMBER != "") {
                    if ((is_numeric(PNUMBER) == false)) {
                        $errorDetected = true;
                        $errorInvalidPNumber = true;
                    } // if number is not blank, but is numeric, passes
                } // if number is blank, passes

                // Errors for already existing values.
                if (Database::update_does_email_exist($_SESSION['logged_in_userID'], $email)) {
                    $errorDetected = true;
                    $errorEmailExists = true;
                }
                if (Database::update_does_user_exist($_SESSION['logged_in_userID'], $username)) {
                    $errorDetected = true;
                    $errorUserExists = true;
                }
                if (Database::update_does_pass_exist($_SESSION['logged_in_userID'], $password)) {
                    $errorDetected = true;
                    $errorPassExists = true;
                }

                // Error for passwords not matching.
                if ((PASSWORD != CPASSWORD) && ($errorNullCPassword == false)) {
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
                    if ($errorNullCPassword == true) {
                        echo "<p>Please re-type your password.</p>\n";
                    }
                    if ($errorInvalidName == true) {
                        echo "<p>Name cannot contain numbers or other symbols.</p>\n";
                    }
                    if ($errorInvalidEmail == true) {
                        echo "<p>Please enter a valid email address.</p>\n";
                    }
                    if ($errorInvalidPNumber == true) {
                        echo "<p>Please enter numbers only for the phone number field or leave blank.</p>";
                    }
                    if ($errorUserExists == true || $errorPassExists == true || $errorEmailExists == true) {
                        echo "<p>Information wasn't able to be changed! Please try inputting different information.</p>";
                    }
                    if ($errorPasswordMatch == true) {
                        echo "<p>Passwords did not match! Please re-enter your password.</p>\n";
                    }
                    exit;
                }
                updateUser();
            }

            // Function for creating a user account
            function updateUser() {
                require_once("../php_includes/Database.php");
                $userID = $_SESSION['logged_in_userID'];
                $username = $_POST['username'];
                $fName = $_POST['fName'];
                $lName = $_POST['lName'];
                $email = $_POST['email'];
                $pNumber = $_POST['pNumber'];
                $password = $_POST['password'];

                if ($password != null) {
                    $password = password_hash($password, PASSWORD_BCRYPT);
                    Database::update_password($userID, $password);
                }

                Database::update_user($userID, $username, $fName, $lName, $email, $pNumber);
                $_SESSION['logged_in_username'] = $username;
                $_SESSION['logged_in_userEmail'] = $email;
                $_SESSION['logged_in_firstName'] = $fName;
                $_SESSION['logged_in_lastName'] = $lName;
                $_SESSION['logged_in_phoneNumber'] = $pNumber;

                echo "<p>Information changed successfully!</p>\n";
            }

            detectErrors(); // calls function

        ?>
    </div>
</body>
</html>
<?php
