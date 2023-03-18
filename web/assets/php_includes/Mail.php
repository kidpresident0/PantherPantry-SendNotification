<?php
class Mail
{
    //$receiver = (insert user's email);

    public static function generate_code(): string
    {
        return bin2hex(random_bytes(16));
    }

    public static function send_verify($receiver)
    {
        $act_code = self::generate_code();

        //OLD OLD OLD Saving just in case! use for testing locally
        // grabs url and cuts off the last two elements
//        $url = explode('/', $_SERVER['SERVER_NAME'] . $_SERVER['REQUEST_URI']);
//        $url = array_filter($url);
//        array_pop($url);
//        array_pop($url);
//        $url = implode('/', $url);
//
//        $activation_link = "http://" . $url . "/pages/verify.php?email=$receiver&code=$act_code";

        // Use this for testing on web server
        $activation_link = "http://www.glassgirder.com/web/assets/pages/verify.php?email=$receiver&code=$act_code";

        // Email
        $subject = "Verify Your Panther Pantry Account";
        $body = "Hello! You have created an account with Panther Pantry. Please click the link below to verify your account.\n$activation_link";
        $sender = "From:pantherpantrynotifs@gmail.com";

        if (mail($receiver, $subject, $body, $sender)) {
            require_once("Database.php");
            echo "<p>A verification email has been sent to $receiver. Please click the link in this email to verify your account.</p>";

            // Store code in the database and connect it to the user
            Database::insert_code($receiver, $act_code);
        } else {
            echo "<p>Sorry, we couldn't send a verification email to your email address! Please try re-typing your info or using a different email address!</p>";
        }
    }
}
