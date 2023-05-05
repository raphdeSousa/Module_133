<?php
include_once('../controllers/Connexion.php');

class UserDBManager
{

    public function checkLogin($user, $password)
    {
        $result = null;
        $connexion = Connexion::getInstance();
        $data = $connexion->selectQuery("select * FROM t_utilisateur where UserName='" . $user . "';");
        if($data != null){
            $pwd = $data[0][2];
            if(password_verify($password, $pwd) == true){
                $result = 'ok';
            }
        }
        return $result;
    }
}