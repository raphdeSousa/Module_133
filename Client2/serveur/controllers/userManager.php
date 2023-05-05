<?php
require_once('../workers/UserDBManager.php');
require_once('../workers/SessionManager.php');


if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    if ($_GET['action'] == "isLogged") {
        $sessionManager = new SessionManager();
        echo $sessionManager->isConnected();
    }
}

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    if ($_POST['action'] == "checklogin") {
        if (isset($_POST['user']) && isset($_POST['password'])) {
            $username = $_POST['user'];
            $password = $_POST['password'];
            $LoginDB = new UserDBManager();
            $result = $LoginDB->checkLogin($username, $password);
            if ($result != null) {
                $sessionManager = new SessionManager();
                $sessionManager->openSession($username);
                echo '<result>'.$result.'</result>';
            }
        }
    }
    if ($_POST['action'] == "disconnect") {
        $sessionManager = new SessionManager();
        echo $sessionManager->destroySession();
    }


}