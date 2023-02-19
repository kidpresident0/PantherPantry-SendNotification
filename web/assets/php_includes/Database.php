<?php

class Database
{
    // LOGIN INFO TO CONNECT TO DATABASE
    const DB_SERVER = "cisdbss.pcc.edu";
    const DB_DATABASE = "234a_Null";
    const DB_USER = "234a_Null";
    const DB_PASSWORD = "456$%^234a_Null";

    const FIND_USER_SQL = <<<QUERY
    SELECT  username, userEmail, firstName
    FROM    USERS
    WHERE   (LOWER(username) = :inputUsername
        OR  LOWER(userEmail) = :inputUserEmail);  
QUERY;

    const FIND_EMAIL_SQL = <<<QUERY_EMAIL
    SELECT  userEmail
    FROM    USERS
    WHERE   (LOWER(userEmail)) = :inputEmail;
QUERY_EMAIL;

    const FIND_USERNAME_SQL = <<<QUERY_USERNAME
    SELECT  username
    FROM    USERS
    WHERE   (LOWER(username)) = :inputUsername;
QUERY_USERNAME;

    const FIND_PASS_SQL = <<<QUERY_PASSWORD
    SELECT  userPassword
    FROM    USERS
    WHERE   (LOWER(userPassword)) = :inputPassword;
QUERY_PASSWORD;
    
    const CREATE_USER_SQL = <<<QUERY_INSERT
    INSERT INTO USERS   (username, firstName, lastName, userEmail, userPassword, userRole)
        VALUES          (:inputUsername, :inputFName, :inputLName, :inputEmail, :inputPassword, 'subscriber');
QUERY_INSERT;
    
    const GET_PASS_SQL = <<<QUERY_GETP
    SELECT  userPassword
    FROM    USERS
    WHERE   (LOWER(username)) = :inputUsername
        OR  (LOWER(userEmail)) = :inputUserEmail; 
QUERY_GETP;

    private static $db = NULL;
    
    private static function connect() {
        if (empty(Database::$db)) {
            Database::$db = new PDO(
                "sqlsrv:Server=" . Database::DB_SERVER . ";Database=" . Database::DB_DATABASE, 
                Database::DB_USER, 
                Database::DB_PASSWORD
            );
        } // END IF
    } // END connect()
    
    public static function find_user($username)
    {
        Database::connect();
        $stmt = Database::$db->prepare(Database::FIND_USER_SQL);
        $stmt->execute([
            ":inputUsername" => strtolower($username),
            ":inputUserEmail" => strtolower($username)
            //":inputPassword" => $password
        ]);
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $result;
    } // END find_user
    
    public static function does_email_exist($email)
    {
        // Code for searching email. Is used in register_account.php to avoid creating duplicate email.
        Database::connect();
        $stmt = Database::$db->prepare(Database::FIND_EMAIL_SQL);
        $stmt->execute([
            ":inputEmail" => strtolower($email)
        ]);
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $result;
    } // END does_email_exist
    
    public static function does_user_exist($username)
    {
        // Code for searching username. Is used in register_account.php to avoid creating duplicate username.
        Database::connect();
        $stmt = Database::$db->prepare(Database::FIND_USERNAME_SQL);
        $stmt->execute([
            ":inputUsername" => strtolower($username)
        ]);
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $result;
    } // END does_user_exist
    
    public static function does_pass_exist($password)
    {
        // Code for searching password. Is used in register_account.php to avoid creating duplicate password.
        Database::connect();
        $stmt = Database::$db->prepare(Database::FIND_PASS_SQL);
        $stmt->execute([
            ":inputPassword" => strtolower($password)
        ]);
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $result;
    } // END does_pass_exist
    
    public static function pass_match($username, $password) 
    {
        Database::connect();
        $stmt = Database::$db->prepare(Database::GET_PASS_SQL);
        $stmt->execute([
            ":inputUsername" => strtolower($username),
            ":inputUserEmail" => strtolower($username) 
        ]);
        $hash = $stmt->fetchColumn();
        $verify = password_verify($password, $hash);
        return $verify;
    }
    
    public static function create_user($username, $fName, $lName, $email, $password) 
    {
        Database::connect();
        $stmt = Database::$db->prepare(Database::CREATE_USER_SQL);
        $stmt->execute([
           ":inputUsername" => $username,
           ":inputFName" => $fName,
           ":inputLName" => $lName,
           ":inputEmail" => $email,
           ":inputPassword" => $password
        ]);
    }
    
} // END Database class


?>
