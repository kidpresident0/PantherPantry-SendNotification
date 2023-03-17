<?php
require_once("../php_includes/User.php");
require_once("../php_includes/Database.php");

echo json_encode(User::find_user($_GET["username"]));