<?php 
  /**
  * Classe Manga
  *
  * Cette classe représente un manga.
  *
  * @version 1.0
  * @author de sousa Raphael 
  * @project projet gestion de manga
  */
  class manga
  {
    /**
    * Variable représentant la pk du manga
    * @access private
    * @var integer
    */
    private $pk_manga;

    /**
    * Variable représentant le nom du manga
    * @access private
    * @var string
    */
    private $nomManga;

    /**
    * Variable représentant le nom du Tome
    * @access private
    * @var string
    */
    private $nomTome;
    
    /**
    * Variable représentant le numero de tome
    * @access private
    * @var integer
    */
    private $numeroTome;

    /**
    * Variable représentant l'image
    * @access private
    * @var string
    */
    private $image;


    /**
    * Constructeur de la classe Manga
    *
    * @param int $pk_pays. PK du pays
    * @param string nom. Nom du pays
    * @param int $dossardCoureur. Numéro de dossard du coureur
    */
    public function __construct($pk_manga, $nomManga, $nomTome, $numeroTome, $image)
    {
      $this->pk_manga = $pk_manga;    
      $this->nomManga = $nomManga;
      $this->nomTome =$nomTome;
      $this->numeroTome =$numeroTome;
      $this->image =$image;

    }
    /**
    * Fonction qui retourne la pk du manga.
    *
    * @return pk du manga.
    */
    public function getPkManga()
    {
      return $this->pk_manga;
    }

    /**
    * Fonction qui retourne le nom du manga.
    *
    * @return nomManga du manga.
    */
    public function getNomManga()
    {
      return $this->nomManga;
    }
    
    /**
    * Fonction qui retourne le nom du tome.
    *
    * @return nomTome du manga.
    */
    public function getNomTome()
    {
      return $this->nomTome;
    }

    /**
    * Fonction qui retourne le numero du tome.
    *
    * @return num du tome.
    */
    public function getNumTome()
    {
      return $this->numeroTome;
    }

    /**
    * Fonction qui retourne l'image du tome.
    *
    * @return img du tome.
    */
    public function getImg()
    {
      return $this->image;
    }

 
    
    /**
    * Fonction qui retourne le contenu du bean au format XML
    * @return le contenu du bean au format XML
    */
    public function toXML()
    {
      $result = '<manga>';
      $result = $result . '<pk_manga>'.$this->getPkManga().'</pk_manga>';
      $result = $result . '<nomManga>'.$this->getNomManga().'</nomManga>';
      $result = $result . '<nomTome>'.$this->getNomTome().'</nomTome>';
      $result = $result . '<numTome>'.$this->getNumTome().'</numTome>';
      $result = $result . '<image>'.$this->getImg().'</image>';
      $result = $result . '</manga>';
      return $result;
    }
  }
?>