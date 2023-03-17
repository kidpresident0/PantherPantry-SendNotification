<?php

class User implements JsonSerializable {
    public $userID;
    public $username;
    public $userEmail;
    public $firstName;
    public $lastName;
    public $userRole;
    public $phoneNumber;
    
    public function __construct($properties)
    {
        $this->username = $properties["username"];
        $this->userID = $properties["userID"];
        $this->userEmail = $properties["userEmail"];
        $this->firstName = $properties["firstName"];
        $this->lastName = $properties["lastName"];
        $this->userRole = $properties["userRole"];
        $this->phoneNumber = $properties["phoneNumber"];
    }

    public static function find_userID($userID)
    {
        return Database::find_user($userID);
    }
    public static function find_user($username)
    {
        return Database::find_user($username);
    }

    public function getUsername() {
        return $this->username;
    }

    public function getUserEmail() {
        return $this->userEmail;
    }

    public function getFirstName() {
        return $this->firstName;
    }

    public function getLastName() {
        return $this->lastName;
    }

    public function getUserRole() {
        return $this->userRole;
    }
    public function getPhoneNumber() {
        return $this->phoneNumber;
    }

    public function jsonSerialize()
    {
        return [
            "username" => $this->username,
            "userEmail" => $this->userEmail,
            "firstName" => $this->firstName,
            "lastName" => $this->lastName,
            "userRole" => $this->userRole,
            "phoneNumber" => $this->phoneNumber
        ];
    }
}
?>