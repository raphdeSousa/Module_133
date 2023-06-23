/*
 * Couche de services HTTP .
 *
 * @author Malcolm Gfeller
 * @version 1.0 / 22.06.2023
 */

var URL = "https://gfellerm01.emf-informatique.ch/javaAPI-Gateway/GatewayServlet?action=";

/**
 * Login function, if the login is successful, we call loginSuccessCallBack, else we call loginErrorCallBack.
 * @param username login name
 * @param password user password
 * @param successCallback success callback
 * @param errorCallBack error callback
 */
function login(username, password, successCallback, errorCallBack) {
  $.ajax({
    type: "POST",
    dataType: "json",
    url: URL + "apiLogin",
    data: "username=" + username + "&password=" + password,
    xhrFields: {
      withCredentials: true,
    },
    async: false,
    crossDomain: true,
    success: successCallback,
    error: errorCallBack
  });
}

/**
 * Adds a manga
 * @param NomDuManga name of the manga
 * @param Image image of the tome
 * @param NumeroDuTome num of the tome
 * @param NomDuTome name of the tome
 * @param successCallback success callback
 * @param errorCallBack error callback
 */
function addManga(NomDuManga, NomDuTome, NumeroDuTome, Image, successCallback, errorCallback) {
  $.ajax({
    type: "POST",
    datatype: 'xml',
    url: URL + "apiAjoutManga",
    data: "nomDuManga=" + NomDuManga + "&nomDuTome=" + NomDuTome + "&numDuTome=" + NumeroDuTome + "&image=" + Image,
    xhrFields: {
      withCredentials: true
    },
    async: false,
    crossDomain: true,
    success: function(){},
    error: function(){}
  });
}

/**
 * Modifies a manga
 * @param NomDuManga name of the manga
 * @param Image image of the tome
 * @param NumeroDuTome num of the tome
 * @param NomDuTome name of the tome
 * @param successCallback success callback
 * @param errorCallBack error callback
 */
function modifyManga(PK, NomDuManga, NomDuTome, NumeroDuTome, Image, successCallback, errorCallback) {
  $.ajax({
    type: "PUT",
    datatype: 'json',
    url: URL + "apiModifyManga",
    data: "Pk=" + PK + "&nomDuManga=" + NomDuManga + "&nomDuTome=" + NomDuTome + "&numDuTome=" + NumeroDuTome + "&image=" + Image,
    xhrFields: {
      withCredentials: true
    },
    async: false,
    crossDomain: true,
    success: successCallback,
    error: errorCallback
  });
}

/**
 * Deletes a manga
 * @param NomDuManga name of the manga
 * @param Image image of the tome
 * @param NumeroDuTome num of the tome
 * @param NomDuTome name of the tome
 * @param successCallback success callback
 * @param errorCallBack error callback
 */
function deleteManga(PK, successCallback, errorCallback) {
  $.ajax({
    type: "DELETE",
    datatype: 'json',
    url: URL + "apiDeleteManga",
    data: "Pk=" + PK,
    xhrFields: {
      withCredentials: true
    },
    async: false,
    crossDomain: true,
    success: successCallback,
    error: errorCallback
  });
}

/**
     * Make an ajax request to check if the user is connected.
     * @param successCallback The function called when the request is successful
     */
function checkLogin(successCallback, errorCallback) {
  $.ajax({
    type: "POST",
    dataType: "text",
    url: URL + "apiCheckLogin",
    xhrFields: {
      withCredentials: true
    },
    async: false,
    crossDomain: true,
    success: successCallback,
    error: errorCallback
  });
}

/**
 * disconnects the user
 * @param successCallback success callback
 * @param errorCallBack error callback
 */
function disconnect(successCallback, errorCallback) {
  $.ajax({
    type: "POST",
    datatype: 'json',
    url: URL + "apiDisconnect",
    xhrFields: {
      withCredentials: true
    },
    async: false,
    crossDomain: true,
    success: successCallback,
    error: errorCallback
  });
}
