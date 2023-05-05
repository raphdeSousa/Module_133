<?php 
include_once('../controllers/Connexion.php');
include_once('../beans/Manga.php');
	
/**
* Classe MangaBDManager
*
* Cette classe permet la gestion des mangas dans la base de données dans l'exercice de debbugage
*
* @version 1.0
* @author de Sousa Raphael 
* @project gestion manga
*/
class MangaDBManager
{
	/**
	* Fonction permettant la lecture des mangas.
	* Cette fonction permet de retourner la liste des manga se trouvant dans la liste
	*
	* @return liste de Manga
	*/
	public function readManga()
	{
		$connexion = Connexion::getInstance();
		$result = '<listeManga>';
		$query = $connexion->executeQuery("select * from t_manga order by NomDuManga and NumeroDuTome;");
		foreach($query as $data){
			$manga = new Manga($data['PK_Manga'], $data['NomDuManga'],$data['NomDuTome'],$data['NumeroDuTome'],$data['Image']);
			$result = $result .$manga->toXML();
		}	
		$result = $result . '</listeManga>';
		echo $result;	
	}

	/**
	* Fonction permettant la lecture des manga mis en favori.
	* Cette fonction permet de retourner la liste des manga favori se trouvant dans la liste
	*
	* @return liste de Manga
	*/
	public function getFavori($username){
        $connexion = Connexion::getInstance();
        $result = '<listeManga>';
        $query = $connexion->selectQuery("select PK_Manga, NomDuManga, NomDuTome, NumeroDuTome, Image FROM tr_manga_utilisateur inner join t_utilisateur on FK_utilisateur = PK_Utilisateur inner join t_manga on FK_manga = PK_Manga where Username ='". $username ."';");
        foreach ($query as $data) {
			$manga = new Manga($data['PK_Manga'], $data['NomDuManga'],$data['NomDuTome'],$data['NumeroDuTome'],$data['Image']);
			$result = $result .$manga->toXML();
        }
        $result = $result . '</listeManga>';
        return $result;
    }
	
    public function isManga($PKManga)
    {
        $result = false;
        $connexion = Connexion::getInstance();
        $query = $connexion->selectQuery("select * FROM t_manga where PK_Manga='" . $PKManga . "';");
        if ($query != null) {
            $result = true;
        }
        return $result;
    }

    public function addManga($PKManga, $username)
    {
        $result = false;
        $connexion = Connexion::getInstance();
        $param = array($PKManga, $username);
        $query = $connexion->executeQuery("insert into tr_manga_utilisateur (FK_manga, FK_utilisateur) VALUES(?,?) ;", $param);
        if ($query != null) {
            $result = true;
        }
        return $query;

    }

    public function deleteManga($PKManga, $username)
    {
        $result = false;
        $connexion = Connexion::getInstance();
        $param = array(':PK_Manga' => $PKManga, ":username" => $username);
        $query = $connexion->executeQuery("delete from tr_manga_utilisateur where FK_manga = :PK_Manga and FK_User in (select FK_utilisateur from t_user where UserName = :username );", $param);
        if ($query != null) {
            $result = true;
        }
        return $result;

    }
}
?>