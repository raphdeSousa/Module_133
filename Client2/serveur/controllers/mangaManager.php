<?php
require_once('../workers/MangaDBManager.php');
require_once('../workers/SessionManager.php');

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    if ($_GET['action'] == "all") {
        $manager = new MangaDBManager();
        echo $manager->readManga();
    }
    if ($_GET['action'] == "favori") {
        $manager = new MangaDBManager();
        $username = $_GET['user'];
        echo $manager->getFavori($username);
    }
}

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    if ($_POST['action'] == "addManga") {
        $sessionManager = new SessionManager();
        $connected = $sessionManager->isConnected();
        if ($connected == true) {
            $username = $_POST['utilisateur'];
            $PKManga = $_POST['PK_Manga'];
            $manager = new MangaDBManager();
            $valide = $manager->isManga($PKManga);
            if ($valide == true) {
                $result = $manager->addManga($PKManga, $username);
                if ($result) {
                    echo $PKManga;
                } else {
                    http_response_code(500);
                }
            } else {
                http_response_code(404);
            }
        } else {
            http_response_code(401);
        }
    }
    if ($_POST['action'] == "deleteManga") {
        $sessionManager = new SessionManager();
        $connected = $sessionManager->isConnected();
        if ($connected == true) {
            $username = $_POST['utilisateur'];
            $PKManga = $_POST['PK_Manga'];
            $manager = new MangaDBManager();
            $valide = $manager->isManga($PKManga);
            if ($valide == true) {
                $result = $manager->deleteManga($PKManga, $username);
                if ($result) {
                    echo $PKManga;
                } else {
                    http_response_code(500);
                }
            } else {
                http_response_code(404);
            }
        } else {
            http_response_code(401);
        }
    }
}