<?php
require_once "User.php";
require_once "Database.php";

use PHPUnit\Framework\TestCase;

class UserTest extends TestCase
{

    public function test__construct()
    {
        $user = new User([
            "username" => "testUsername",
            "userEmail" => "test@email.com",
            "firstName" => "TestnameF",
            "lastName" => "TestnameL",
            "userRole" => "subscriber"
        ]);
        $this->assertEquals("testUsername", $user->getUsername());
        $this->assertEquals("test@email.com", $user->getUserEmail());
        $this->assertEquals("TestnameF", $user->getFirstName());
        $this->assertEquals("TestnameL", $user->getLastName());
        $this->assertEquals("subscriber", $user->getUserRole());
    }

    public function testGetUsername()
    {
        $user = new User(["username" => "testUsername", "userEmail" => "", "firstName" => "", "lastName" => "", "userRole" => ""]);
        $this->assertEquals("testUsername", $user->getusername());
    }

    public function testGetUserEmail()
    {
        $user = new User(["username" => "", "userEmail" => "test@email.com", "firstName" => "", "lastName" => "", "userRole" => ""]);
        $this->assertEquals("test@email.com", $user->getUserEmail());
    }

    public function testGetFirstName()
    {
        $user = new User(["username" => "", "userEmail" => "", "firstName" => "TestnameF", "" => "TestnameL", "userRole" => ""]);
        $this->assertEquals("TestnameF", $user->getFirstName());
    }

    public function testGetLastName()
    {
        $user = new User(["username" => "", "userEmail" => "", "firstName" => "", "lastName" => "TestnameL", "userRole" => ""]);
        $this->assertEquals("TestnameL", $user->getLastName());
    }

    public function testGetUserRole()
    {
        $user = new User(["username" => "", "userEmail" => "", "firstName" => "", "lastName" => "", "userRole" => "subscriber"]);
        $this->assertEquals("subscriber", $user->getUserRole());
    }

    public function testFind_user_no_results()
    {
        $users = User::find_user("invalidUser");
        $this -> assertCount(0, $users);
    }

    public function testFind_user_valid_search()
    {
        $users = User::find_user("CerealFan");
        $expectedUser =  array (
            0 =>
                new User(array(
                    'username' => 'CerealFan',
                    'userEmail' => 'wiredworld@where.com',
                    'firstName' => 'Lain',
                    'lastName' => 'Iwakura',
                    'userRole' => 'subscriber'
                ))
        );
        $this->assertEquals($expectedUser, $users);
    }

    public function testJsonSerialize()
    {
        $user = new User([
            "username" => "testUsername",
            "userEmail" => "test@email.com",
            "firstName" => "TestnameF",
            "lastName" => "TestnameL",
            "userRole" => "subscriber"
        ]);
        $this->assertEquals([
            "username" => "testUsername",
            "userEmail" => "test@email.com",
            "firstName" => "TestnameF",
            "lastName" => "TestnameL",
            "userRole" => "subscriber"
        ], $user->jsonSerialize());
    }
}
