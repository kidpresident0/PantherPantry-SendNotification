<?php

class Database
{
    // LOGIN INFO TO CONNECT TO DATABASE
    const DB_SERVER = "cisdbss.pcc.edu";
    const DB_DATABASE = "234a_Null";
    const DB_USER = "234a_Null";
    const DB_PASSWORD = "456$%^234a_Null";

    const FIND_USER_SQL = <<<QUERY
    SELECT  userID, username, userEmail, firstName, lastName, userRole, phoneNumber
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
    INSERT INTO USERS   (username, firstName, lastName, userEmail, userPassword, userRole, phoneNumber, receiveNotifications, notificationType, verified, verifyCode)
        VALUES          (:inputUsername, :inputFName, :inputLName, :inputEmail, :inputPassword, 'subscriber', '', 'Yes', 'Email', 'false', '');
QUERY_INSERT;

    const INSERT_CODE_SQL = <<<QUERY_UPDATE
    UPDATE  USERS
    SET     verifyCode = :inputCode
    WHERE   (LOWER(userEmail)) = :inputEmail;
QUERY_UPDATE;

    const GET_CODE_SQL = <<<QUERY_GETC
    SELECT  verifyCode
    FROM    USERS
    WHERE   (LOWER(userEmail)) = :inputEmail
        AND verifyCode = :inputCode;
QUERY_GETC;

    const VERIFY_CODE_SQL = <<<QUERY_GETC
    UPDATE  USERS
    SET     verified = 'true'
    WHERE   (LOWER(userEmail)) = :inputEmail
        AND verifyCode = :inputCode;
QUERY_GETC;

    const GET_PASS_SQL = <<<QUERY_GETP
    SELECT  userPassword
    FROM    USERS
    WHERE   (LOWER(username)) = :inputUsername
        OR  (LOWER(userEmail)) = :inputUserEmail; 
QUERY_GETP;

    const UPDATE_FIND_EMAIL_SQL = <<<QUERY_FIND
    SELECT  userEmail
    FROM    USERS
    WHERE   userID != :inputID
        AND LOWER(userEmail) = :inputEmail;
QUERY_FIND;

    const UPDATE_FIND_USERNAME_SQL = <<<QUERY_FIND
    SELECT  username
    FROM    USERS
    WHERE   userID != :inputID
        AND LOWER(username) = :inputUser
QUERY_FIND;

    const UPDATE_FIND_PASS_SQL = <<<QUERY_FIND
    SELECT  userPassword
    FROM    USERS
    WHERE   userID != :inputID
        AND userPassword = :inputPass
QUERY_FIND;

    const UPDATE_USER_SQL = <<<QUERY_UPDATE
    UPDATE  USERS
    SET     username = :inputUsername,
            firstName = :inputFName,
            lastName = :inputLName,
            userEmail = :inputEmail,
            phoneNumber = :inputPNumber
    WHERE   userID = :inputID;
QUERY_UPDATE;

    const UPDATE_PASS_SQL = <<<QUERY_UPDATE
    UPDATE  USERS
    SET     userPassword = :inputPass
    WHERE   userID = :inputID;
QUERY_UPDATE;

    const UPDATE_NOTIFS_SQL = <<<QUERY_UPDATE
    UPDATE  USERS
    SET     receiveNotifications = :inputReceive,
            notificationType = :inputType
    WHERE   userID = :inputID;
QUERY_UPDATE;

    const GET_NOTIFS_SQL = <<<QUERY_FIND
    SELECT  notificationType
    FROM    USERS
    WHERE   userID = :inputID;
QUERY_FIND;

    const DELETE_USER_SQL = <<<QUERY_DELETE
    DELETE 
        FROM    USERS 
        WHERE   userID = :inputID;
QUERY_DELETE;


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
        ]);

        $users = $stmt->fetchAll(PDO::FETCH_ASSOC);
        $userList = [];
        foreach ($users as $user) {
            $userList[] = new User($user);
        }
        return $userList;
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

    public static function insert_code($userEmail, $verifyCode)
    {
        Database::connect();
        $stmt = Database::$db->prepare(Database::INSERT_CODE_SQL);
        $stmt->execute([
            ":inputEmail" => strtolower($userEmail),
            ":inputCode" => $verifyCode
        ]);
    }
    public static function find_code($userEmail, $userCode)
    {
        Database::connect();
        $stmt = Database::$db->prepare(Database::GET_CODE_SQL);
        $stmt->execute([
            ":inputEmail" => strtolower($userEmail),
            ":inputCode" => strtolower($userCode)
        ]);
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $result;
    }
    public static function code_match($userEmail, $verifyCode)
    {
        Database::connect();
        $stmt = Database::$db->prepare(Database::VERIFY_CODE_SQL);
        $stmt->execute([
            ":inputEmail" => strtolower($userEmail),
            ":inputCode" => $verifyCode
        ]);
        return true;
    }

    public static function update_does_email_exist($userID, $userEmail)
    {
        Database::connect();
        $stmt = Database::$db->prepare(Database::UPDATE_FIND_EMAIL_SQL);
        $stmt->execute([
            ":inputID" => $userID,
            ":inputEmail" => strtolower($userEmail)
        ]);
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $result;
    }

    public static function update_does_user_exist($userID, $username)
    {
        Database::connect();
        $stmt = Database::$db->prepare(Database::UPDATE_FIND_USERNAME_SQL);
        $stmt->execute([
            ":inputID" => $userID,
            ":inputUser" => strtolower($username)
        ]);
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $result;
    }

    public static function update_does_pass_exist($userID, $password)
    {
        Database::connect();
        $stmt = Database::$db->prepare(Database::UPDATE_FIND_PASS_SQL);
        $stmt->execute([
            ":inputID" => $userID,
            ":inputPass" => $password
        ]);
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $result;
    }

    public static function update_user($userID, $username, $fName, $lName, $email, $pNumber)
    {
        Database::connect();
        $stmt = Database::$db->prepare(Database::UPDATE_USER_SQL);
        $stmt->execute([
            ":inputID" => $userID,
            ":inputUsername" => $username,
            ":inputFName" => $fName,
            ":inputLName" => $lName,
            ":inputEmail" => $email,
            ":inputPNumber" => $pNumber
        ]);
    }

    public static function update_password($userID, $password)
    {
        Database::connect();
        $stmt = Database::$db->prepare(Database::UPDATE_PASS_SQL);
        $stmt->execute([
            ":inputID" => $userID,
            ":inputPass" => $password
        ]);
    }

    public static function set_notifs($userID, $receive, $type)
    {
        Database::connect();
        $stmt = Database::$db->prepare(Database::UPDATE_NOTIFS_SQL);
        $stmt->execute([
            ":inputID" => $userID,
            ":inputReceive" => $receive,
            ":inputType" => $type
        ]);
    }

    public static function find_notifs($userID)
    {
        Database::connect();
        $stmt = Database::$db->prepare(Database::GET_NOTIFS_SQL);
        $stmt->execute([
           ":inputID" => $userID
        ]);
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $result;
    }

    public static function delete_user($userID)
    {
        Database::connect();
        $stmt = Database::$db->prepare(Database::DELETE_USER_SQL);
        $stmt->execute([
            ":inputID" => $userID
        ]);
    }

} // END Database class


?>
