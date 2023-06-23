/*
 * Contrôleur de la vue "index.html"
 *
 * @author de Sousa Raphael
 * @version 1.0 / 22.06.2023
 */
let user = null;
/**
 * Méthode appelée lors du retour avec succès de la liste des mangas
 * @param {type} data
 * @param {type} text
 * @param {type} jqXHR
 * @returns {undefined}
 */
function chargerMangaSuccess(data, text, jqXHR) {
  var jsonArray = JSON.parse(data);
  var txt = '';
  for (var i = 0; i < jsonArray.length; i++) {
    var manga = new Manga();
    manga.setNomDuManga(jsonArray[i].nomDuManga);
    manga.setPk(jsonArray[i].pk_manga);
    manga.setNomDuTome(jsonArray[i].nomDuTome);
    manga.setNumeroDuTome(jsonArray[i].numeroDuTome);
    manga.setImage(jsonArray[i].image);
    txt = txt + "<div class=manga><table><tbody><tr><td>" + "<img id=myimage src=../../serveur/images/" + manga.getImg() + "></img>" + "</td></tr><tr><td>" + manga.getNomDuTome() + " - " + manga.getNomDuManga() + ", <br> Tome " + manga.getNumeroDuTome() + "<br></td></tr><tr><td><button class=butFavori id=manga" + manga.getPk() + " >Ajouter à la collection</button>" + "</td></tr></tbody></table></div>";
  }
  document.getElementById("listeManga").innerHTML = txt;
  var favori = $('#favori');
  document.getElementById('favori').innerHTML = "Favori";
  favori.off('click');
  favori.click(function (event) { getFavori(user, chargerFavori, chargerErrorManga) });
}

function chargerFavori(data, text, jqXHR) {
  var txt = '';
  $(data).find("manga").each(function () {
    var manga = new Manga();
    manga.setNomDuManga($(this).find("nomManga").text());
    manga.setPk($(this).find("pk_manga").text());
    manga.setNomDuTome($(this).find("nomTome").text());
    manga.setNumeroDuTome($(this).find("numTome").text());
    manga.setImage($(this).find("image").text());
    txt = txt + "<div class=manga><table><tbody><tr><td>" + "<img id=myimage src=../serveur/images/" + manga.getImg() + "></img>" + "</td></tr><tr><td>" + manga.getNomDuTome() + " - " + manga.getNomDuManga() + ", <br> Tome " + manga.getNumeroDuTome() + "<br></td></tr><tr><td><button class=butFavori id=manga" + manga.getPk() + " >Ajouter à la collection</button>" + "</td></tr></tbody></table></div>";

  });
  document.getElementById("listeManga").innerHTML = txt;
  var favori = $('#favori');
  document.getElementById('favori').innerHTML = "Retour à la vue global";
  favori.off('click');
  favori.click(function (event) { getManga(chargerMangaSuccess, chargerErrorManga) });
}

function callLogin() {
  var username = $("#username").val();
  var password = $("#password").val();
  login(username, password, loginSuccess, loginError);
  $("#username").val("");
  $("#password").val("");
}

function loginSuccess(data) {
  if (data.result === true) {
    alert("Connexion réussie");
    applyCSSLogin();
  } else {
    alert("Nom d'utilisateur ou mot de passe invalide");
  }
}

function checkSuccess(data) {
  if (data.trim() === "{\"result\": false}") {
  }
}

function loginError() {
  alert("Erreur de connexion");
}

function calldisconnect() {
  disconnect(disconnectSuccess, disconnectError);
}

function disconnectSuccess() {
  alert("Déconnexion réussie");
  location.reload();
}

function disconnectError() {
  alert("Erreur de déconnexion");
}

function chargerError(request, status, error) {
  alert("L'identifiant ou le mot de passe est faux");
}
function chargerErrorManga(request, status, error) {
  alert("Erreur lors de l'ajoute des mangas dans les favoris");
}
/**
 * vas afficher les bouttons qui sont cacher lors du login
 */
function applyCSSLogin() {
  $("#favori").css("visibility", "visible");
  $(".butFavori").css("visibility", "visible");
  var deconnexion = $('#connecter');
  document.getElementById('connecter').innerHTML = "Déconnexion";
  deconnexion.off('click');
  deconnexion.click(function (event) { disconnect(applyCSSDisconnect, chargerError); });
  var ajouter = $('.butFavori');
  ajouter.click(function (event) {
    pk = this.id.slice(5);
    currentUser(setUser, chargerError);
    addManga(pk, user, mangaAjoute, chargerErrorManga);
  });
  ajouter.each(function () {
    $pk = this.id.slice(3);
    mangaDelete($pk);
  })
}

function setUser(data) {
  user = data;
}

function verifyLogged(data) {
  if (data) {
    applyCSSLogin();
  }
}

/**
 * Méthode appelée en cas d'erreur lors de la lecture des mangas
 * @param {type} request
 * @param {type} status
 * @param {type} error
 * @returns {undefined}
 */
function chargerMangaError(request, status, error) {
  alert("Erreur lors de la lecture des mangas: " + error);
}

/**
 * Méthode appelée en cas d'erreur lors de la lecture des skieurs
 * @param {type} request
 * @param {type} status
 * @param {type} error
 * @returns {undefined}
 */
function chargerUtilisateurError(request, status, error) {
  alert("Erreur lors de la lecture des utilisateur: " + error);
}

/**
 * Méthode "start" appelée après le chargement complet de la page
 * @param {type} data
 */
$(document).ready(function () {
  var cmbManga = $("#cmbManga");
  var manga = '';
  var connecter = $('#connecter');
  var favori = $('.favori')
  $.getScript("helpers/dateHelper.js", function () {
    console.log("dateHelper.js chargé !");
  });
  $.getScript("beans/manga.js", function () {
    console.log("manga.js chargé !");
  });
  $.getScript("beans/utilisateur.js", function () {
    console.log("utilisateur.js chargé !");
  });
  $.getScript("services/servicesHttp.js", function () {
    console.log("servicesHttp.js chargé !");
    getManga(chargerMangaSuccess, chargerMangaError);
    checkLogin(checkSuccess, loginError);
  });
  cmbManga.change(function (event) {
    manga = this.options[this.selectedIndex].value;
    chargerUtilisateur(JSON.parse(manga).pk, chargerUtilisateurSuccess, chargerUtilisateurError);
  });
  favori.click(function (event) {
    getFavori(user, chargerFavori, chargerError);
  })
});

