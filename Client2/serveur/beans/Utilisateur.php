<?php
/**
* Constructeur de la classe Utilisateur
*
* @param int $pk_pays. PK de l'utilisateur
* @param string nom. Nom du pays
* @param int $dossardCoureur. Numéro de dossard du coureur
*/
public function __construct($pk_utilisateur, $username)
{
    $this->pk_utilisateur = $pk_utilisateur;    
    $this->username = $username;
}

/**
* Fonction qui retourne la pk du manga.
*
* @return pk du manga.
*/
public function getPk_utilisateur()
{
    return $this->pk_utilisateur;
}

/**
* Fonction qui retourne la pk du manga.
*
* @return pk du manga.
*/
public function getUsername()
{
    return $this->username;
}

echo password_hash('dsr', PASSWORD_DEFAULT);
