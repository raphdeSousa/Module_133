<?php

class SessionManager
{

    public function __construct()
    {
        session_start();
    }

    public function openSession($username)
    {
        $_SESSION['username'] = $username;
        $_SESSION['isLogged'] = true;
    }

    public function isConnected()
    {
        $connected = false;
        if (session_status() == 2 && isset($_SESSION['isLogged'])) {
            $connected = true;
        }
        return $connected;
    }

    public function destroySession(){
        $result = false;
        if (isset($_SESSION)) {
            session_unset();
            $result = true;
        }
        return $result;
    }
}