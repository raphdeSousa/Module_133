/*
 * IndexCtrl de mon client
 *
 * @author Malcolm Gfeller
 * @version 1.0 / 22.06.2023
 */

$().ready(function () {
    checkLogin(checkSuccess, servError);
});

function callLogin() {
    var username = $("#username").val();
    var password = $("#password").val();
    login(username, password, loginSuccess, servError);
    $("#username").val("");
    $("#password").val("");
}

function loginSuccess(data) {
    if (data.result === true) {
        alert("Connexion réussie");
        $("#btnGestion").show();
    } else {
        alert("Nom d'utilisateur ou mot de passe invalide");
    }
}

function checkSuccess(data) {
    if (data.trim() === "{\"result\": false}") {
        $("#btnGestion").hide();
    } 
}

function infoForAdd() {
    document.getElementById("resultBtnPressed").innerHTML = "";

    document.getElementById("resultBtnPressed").innerHTML +=
        "<div>" +
        "<div>" +
        "<label>nomDuManga</label>" +
        "<input name='nomDuManga' id='nomDuManga'/>" +

        "<label>nomDuTome</label>" +
        "<input name='nomDuTome' id='nomDuTome'/>" +

        "<label>numDuTome</label>" +
        "<input name='numDuTome' id='numDuTome'/>" +

        "<label>image</label>" +
        "<input name='image' id='image'/>" +
        "</div>" +

        "<button onclick='callAddManga()'>Ajouter</button>" +
        "</div>";
}

function infoForModify() {
    document.getElementById("resultBtnPressed").innerHTML = "";

    document.getElementById("resultBtnPressed").innerHTML +=
        "<div>" +
        "<label>PK</label>" +
        "<input name='PK' id='PK'/>" +

        "<label>nomDuManga</label>" +
        "<input name='nomDuManga' id='nomDuManga'/>" +

        "<label>nomDuTome</label>" +
        "<input name='nomDuTome' id='nomDuTome'/>" +

        "<label>numDuTome</label>" +
        "<input name='numDuTome' id='numDuTome'/>" +

        "<label>image</label>" +
        "<input name='image' id='image'/>" +
        "</div>";
}

function infoForDelete() {
    document.getElementById("resultBtnPressed").innerHTML = "";

    document.getElementById("resultBtnPressed").innerHTML +=
        "<div>" +
        "<label>PK</label>" +
        "<input name='PK' id='PK'/>" +
        "</div>";
}

/**
 * Adds an article with the filled fields. If any of the field isn't filled, we show an alert.
 */
function callAddManga() {
    var nomDuManga = $("#nomDuManga").val();
    var nomDuTome = $("#nomDuTome").val();
    var numDuTome = $("#numDuTome").val();
    var image = $("#image").val();
    if (nomDuManga === "" || image === "" || nomDuTome === "" || numDuTome === "") {
        alert("Please fill all the fields");
        return;
    } else {
        addManga(nomDuManga, nomDuTome, numDuTome, image, addSuccess, servError);
    }
}

function addSuccess(data) {
    console.log(data);
    if(data.trim() === "Manga ajoute"){
        alert("Votre manga a été ajouté");
        $("#nomDuManga").val("");
        $("#nomDuTome").val("");
        $("#numDuTome").val("");
        $("#image").val("");
    } else {
        alert("Les informations entrées sont invalides");
    }
}

/**
 * Adds an article with the filled fields. If any of the field isn't filled, we show an alert.
 */
function callModifyManga() {
    var PK = $("#PK").val();
    var nomDuManga = $("#nomDuManga").val();
    var nomDuTome = $("#nomDuTome").val();
    var numDuTome = $("#numDuTome").val();
    var image = $("#image").val();
    if (PK === "" || nomDuManga === "" || image === "" || nomDuTome === "" || numDuTome === "") {
        alert("Please fill all the fields");
        return;
    } else {
        modifyManga(PK, nomDuManga, nomDuTome, numDuTome, image, modifySuccess, servError);
    }
}

function modifySuccess() {
    alert("Votre manga a été modifié");
    $("#PK").val("");
    $("#nomDuManga").val("");
    $("#nomDuTome").val("");
    $("#numDuTome").val("");
    $("#image").val("");
}

/**
 * Adds an article with the filled fields. If any of the field isn't filled, we show an alert.
 */
function callDeleteManga() {
    var PK = $("#PK").val();
    if (PK === "") {
        alert("Please fill the field");
        return;
    } else {
        deleteManga(PK, deleteSuccess, servError);
    }
}

function deleteSuccess() {
    alert("Votre manga a été supprimé");
    $("#PK").val("");
}

function calldisconnect() {
    disconnect(disconnectSuccess, servError);
}

function disconnectSuccess() {
    alert("Déconnexion réussie");
    location.reload();
}

function servError() {
    alert("Erreur de connexion au serveur");
}