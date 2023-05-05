<?php
include_once('configConnexion.php');

class Connexion {

	private static $_instance = null;
	private $pdo;
	public static function getInstance()
    {
        if (is_null(self::$_instance)) {
            self::$_instance = new Connexion();
        }
        return self::$_instance;
    }
	
	/**
     * Fonction d'ouvrir une connexion à la base de données.
     */
    public function __construct() {
    	try {
			$this->pdo = new PDO(DB_TYPE.':host='.DB_HOST.';dbname='.DB_NAME, DB_USER, DB_PASS, array(
				PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES utf8',
				PDO::ATTR_PERSISTENT=>true
			)
		);
		}catch (PDOException $e) {
    		print "Erreur !: " . $e->getMessage() . "<br/>";
    		die();
		}
	}

	/**
     * Fonction permettant de selectioner une requête MySQL.
	 * 
	 * @param String $query. Requête à selectioner.
     */
	public function selectQuery($query)
    {
        try {
            $queryPrepared = $this->pdo->prepare($query);
            $queryPrepared->execute();
            return $queryPrepared->fetchAll();
        } catch (PDOException $e) {
            print "Erreur !: " . $e->getMessage() . "<br/>";
            die();
        }
    }

	/**
     * Fonction permettant d'exécuter une requête MySQL.
	 * 
	 * @param String $query. Requête à exécuter.
     */
    public function executeQuery($query) {
    	try {
	        $queryRes =  $this->pdo->query($query);		
	        return  $queryRes->fetchAll();
      }catch (PDOException $e) {
          print "Erreur !: " . $e->getMessage() . "<br/>";
          die();
      }
    }

}

?>
