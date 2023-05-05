/*
 * Couche de services HTTP (worker).
 *
 * @author de Sousa Raphael
 * @version 1.0 / 13-SEP-2013
 */

var BASE_URL = "../serveur/controllers/";

/**
 * Fonction permettant de demander la liste des mangas au serveur.
 * @param {type} Fonction de callback lors du retour avec succès de l'appel.
 * @param {type} Fonction de callback en cas d'erreur.
 */
function chargerManga(successCallback, errorCallback) {
  $.ajax({
    type: "GET",
    dataType: "xml",
    url: BASE_URL + "mangaManager.php",
    data: 'action=all',
    success: successCallback,
    error: errorCallback
  });
}

/**
 * Fonction permettant d'obtenir la liste des mangas favori .
 * @param {type} username. l'username de l'utilisateur.
 * @param {type} Fonction de callback lors du retour avec succès de l'appel.
 * @param {type} Fonction de callback en cas d'erreur.
 */
function getFavori(username, successCallback, errorCallback){
  let param = "user=" + username + '&action=favori';
  $.ajax({
      type: "GET",
      datatype: "XML",
      data: param,
      url: BASE_URL + "mangaManager.php",
      success: successCallback,
      error: errorCallback
  });
}

/**
 * Fonction permettant de ce connecter au compt priver .
 * @param {type} username. l'username de l'utilisateur.
 * @param {type} password. le password de l'utilisateur.
 * @param {type} Fonction de callback lors du retour avec succès de l'appel.
 * @param {type} Fonction de callback en cas d'erreur.
 */
function login(username, password, successCallback, errorCallback) {
  let param = "user=" + username + "&password=" + password + "&action=checklogin";
  $.ajax({
      type: "POST",
      data: param,
      dataType: 'xml',
      url: BASE_URL + "userManager.php",
      success: successCallback,
      error: errorCallback
  });
}

/**
 * Fonction permettant de ce déconnecter du compt priver.
 * @param {type} Fonction de callback lors du retour avec succès de l'appel.
 * @param {type} Fonction de callback en cas d'erreur.
 */
function disconnect(successCallback, errorCallback){
  $.ajax({
      type: "POST",
      datatype: 'xml',
      data: "action=disconnect",
      url: BASE_URL + "userManager.php",
      success: successCallback,
      error: errorCallback
  });
}

/**
 * Fonction permet de retourner si nous somme loger ou pas
 * @param {type} Fonction de callback lors du retour avec succès de l'appel.
 * @param {type} Fonction de callback en cas d'erreur.
 */
function isLogged(successCallback, errorCallback) {
  $.ajax({
      type: "GET",
      datatype: 'xml',
      data: 'action=isLogged',
      url: BASE_URL + "userManager.php",
      success: successCallback,
      error: errorCallback
  });
}

/**
 * Fonction permettant d'ajouter un manga dans les favori.
 * @param {type} username. l'username de l'utilisateur.
 * @param {type} PKManga. le password de l'utilisateur.
 * @param {type} Fonction de callback lors du retour avec succès de l'appel.
 * @param {type} Fonction de callback en cas d'erreur.
 */
function addManga(PKManga, username, successCallback, errorCallback) {
  let param = "PK_Manga=" + PKManga + "&utilisateur=" + username + "&action=addManga";
  $.ajax({
      type: "POST",
      datatype: 'json',
      data: param,
      url: BASE_URL + "mangaManager.php",
      success: successCallback,
      error: errorCallback
  });
}

/**
 * Fonction permettant de de suprimer un manga des favori .
 * @param {type} username. l'username de l'utilisateur.
 * @param {type} password. le password de l'utilisateur.
 * @param {type} Fonction de callback lors du retour avec succès de l'appel.
 * @param {type} Fonction de callback en cas d'erreur.
 */
function deleteManga(PKManga, username, successCallback, errorCallback) {
  let param = "PK_Manga=" + PKManga + "&utilisateur=" + username + "&action=deleteManga";
  $.ajax({
      type: "POST",
      datatype: 'json',
      data: param,
      url: BASE_URL + "mangaManager.php",
      success: successCallback,
      error: errorCallback
  });
}

/**
 * Fonction permettant de ce connecter au compt priver .
 * @param {type} Fonction de callback lors du retour avec succès de l'appel.
 * @param {type} Fonction de callback en cas d'erreur.
 */
function currentUser(successCallback, errorCallback) {
  $.ajax({
      type: "GET",
      datatype: 'json',
      data: 'action=currentUser',
      url: BASE_URL + "userManager.php",
      success: successCallback,
      error: errorCallback,
  });
}
