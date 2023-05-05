/*
 * Contrôleur de la vue "index.html"
 *
 * @author de Sousa Raphael
 * @version 1.0 / 27.02.2023
 */
let user = null;
/**
 * Méthode appelée lors du retour avec succès de la liste des mangas
 * @param {type} data
 * @param {type} text
 * @param {type} jqXHR
 * @returns {undefined}
 */
function chargerMangaSuccess(data, text, jqXHR)
{    
	var txt ='';
    $(data).find("manga").each(function() {
      var manga = new Manga();
      manga.setNomDuManga($(this).find("nomManga").text());
      manga.setPk($(this).find("pk_manga").text());
      manga.setNomDuTome($(this).find("nomTome").text());
      manga.setNumeroDuTome($(this).find("numTome").text());
      manga.setImage($(this).find("image").text());
      console.log(this);
      txt = txt + "<div class=manga><table><tbody><tr><td>"+"<img id=myimage src=../serveur/images/" + manga.getImg() +"></img>" + "</td></tr><tr><td>" + manga.getNomDuTome() + " - " +manga.getNomDuManga() + ", <br> Tome " +manga.getNumeroDuTome() +  "<br></td></tr><tr><td><button class=butFavori id=manga" + manga.getPk()  + " >Ajouter à la collection</button>" + "</td></tr></tbody></table></div>";

    });  
    document.getElementById("listeManga").innerHTML = txt;
    var favori = $('#favori');
    document.getElementById('favori').innerHTML = "Favori";
    favori.off('click');
    favori.click(function (event) { getFavori(user, chargerFavori, chargerErrorManga) });
}

function chargerFavori(data, text, jqXHR)
{    
	var txt ='';
    $(data).find("manga").each(function() {
      var manga = new Manga();
      manga.setNomDuManga($(this).find("nomManga").text());
      manga.setPk($(this).find("pk_manga").text());
      manga.setNomDuTome($(this).find("nomTome").text());
      manga.setNumeroDuTome($(this).find("numTome").text());
      manga.setImage($(this).find("image").text());
      console.log(this);
      txt = txt + "<div class=manga><table><tbody><tr><td>"+"<img id=myimage src=../serveur/images/" + manga.getImg() +"></img>" + "</td></tr><tr><td>" + manga.getNomDuTome() + " - " +manga.getNomDuManga() + ", <br> Tome " +manga.getNumeroDuTome() +  "<br></td></tr><tr><td><button class=butFavori id=manga" + manga.getPk()  + " >Ajouter à la collection</button>" + "</td></tr></tbody></table></div>";

    });  
    document.getElementById("listeManga").innerHTML = txt;
    var favori = $('#favori');
    document.getElementById('favori').innerHTML = "Retour à la vue global";
    favori.off('click');
    favori.click(function (event) { chargerManga(chargerMangaSuccess, chargerErrorManga) });
  }


/**
 * Vas checker le login pour voirsi il a des identifient corect
 */
function checkLogin() {
  // récupère les objets InputText du formulaire HTML
  let username = document.getElementById("txtLogin").value;
  let password = document.getElementById("txtPassword").value;
  user = username;
  if (username == "" || password == "") {
      alert("Veuillez entrer des identifiants valides")
  } else {
      login(username, password, OKLogin, chargerError);
  }

}

//control que le login est ok ou pas
function OKLogin(data, text, jqXHR) {
  if ($(data).find("result").text() == 'ok') {
      applyCSSLogin();
  } else {
      alert("Login ou mot de passe invalide, veuillez réessayer");
  }
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
/**
 * dissimution des bouttons "Affichage des tomes obtenus" et des bouttons des chaques tomes.
 * 
 */
function applyCSSDisconnect(data){
  if(data){
      $("#favori").css("visibility", "hidden");
      $(".butFavori").css("visibility", "hidden");
      var connexion = $('#connecter');
      document.getElementById('connecter').innerHTML = "Connexion";
      connexion.off('click');
      connexion.click(function (event) { checkLogin();});
      chargerManga(chargerMangaSuccess, chargerError);
  }
}

function mangaAjoute(data) {
  if (data) {
      var manga = $("#manga" + data);
      manga.css("background-color", "red");
      manga.text("Retirer le tome de la mangatek");
      manga.off("click");
      manga.click(function (event) {
          pk = this.id.slice(5);
          currentUser(setUser, chargerError);
          deleteManga(pk, user, mangaDelete, chargerError);
      })
  }
}


function mangaDelete(data) {
  if (data) {
      var manga = $("#manga" + data);
      manga.css("background-color", "lightgreen");
      manga.text("Ajouter le tome de à mangatek");
      manga.off("click");
      manga.click(function (event) {
          pk = this.id.slice(5);
          currentUser(setUser, chargerError);
          addManga(pk, user, mangaAjoute, chargerError);
      })
  }
}

function setUser(data) {
  user=data;
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
$(document).ready(function() {
  var cmbManga = $("#cmbManga");
  var manga = '';
  var connecter = $('#connecter');
  var favori = $('.favori')
  $.getScript("javascripts/helpers/dateHelper.js", function() {
    console.log("dateHelper.js chargé !");
  });
  $.getScript("javascripts/beans/manga.js", function() {
    console.log("manga.js chargé !");
  });
  $.getScript("javascripts/beans/utilisateur.js", function() {
    console.log("utilisateur.js chargé !");
  });
  $.getScript("javascripts/services/servicesHttp.js", function() {
    console.log("servicesHttp.js chargé !");
    chargerManga(chargerMangaSuccess, chargerMangaError);
  });
  cmbManga.change(function(event) {
    manga = this.options[this.selectedIndex].value;
    chargerUtilisateur(JSON.parse(manga).pk, chargerUtilisateurSuccess, chargerUtilisateurError);
  });
  connecter.click(function(event){
    checkLogin();
  });
  favori.click(function (event) {
    getFavori(user, chargerFavori, chargerError);
  })


  
});

