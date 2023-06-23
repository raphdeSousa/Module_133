/*
 * Couche de services HTTP .
 *
 * @author de Sousa Raphael
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
  /**
   * Get all the manga
   * @param successCallback success callback
   * @param errorCallBack error callback
   */
  function getManga(successCallback, errorCallBack) {
    $.ajax({
      type: "GET",
      dataType: "json",
      url: URL +"apiGetManga",
      xhrFields: {
        withCredentials: true
      },
      async: false,
      crossDomain: true,
      success: successCallback,
      error: errorCallBack
    });
  }

  /**
   * Get all the favoris
   * @param successCallback success callback
   * @param errorCallBack error callback
   */
  function getFavoris(successCallback, errorCallBack) {
    $.ajax({
      type: "GET",
      dataType: "json",
      data: data,
      url: URL +"apiGetFavoris",
      xhrFields: {
        withCredentials: true
      },
      async: false,
      crossDomain: true,
      success: successCallback,
      error: errorCallBack
    });
  }


  /**
   * adds an favoris
   * @param FK_manga fk of the manga
   * @param FK_user fk of the user
   * @param successCallback success callback
   * @param errorCallBack error callback
   */
  function addFavoris(FK_manga, FK_user, successCallback, errorCallback) {
    $.ajax({
      type: "POST",
      datatype: 'json',
      data: data +"fkManga=" + FK_manga + "&fkUser=" + FK_user,
      url: URL +"apiGetFavoris",
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
   * adds an favoris
   * @param FK_manga fk of the manga
   * @param FK_user fk of the user
   * @param successCallback success callback
   * @param errorCallBack error callback
   */
  function delFavoris(FK_manga, FK_user, successCallback, errorCallback) {
    $.ajax({
      type: "DELETE",
      datatype: 'json',
      data: data + "fkManga=" + FK_manga + "&fkUser=" + FK_user,
      url: URL +"apiDeleteFavoris",
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
  function disconnect(successCallback, errorCallBack) {
    $.ajax({
      type: "POST",
      dataType: "json",
      data: "action=logout",
      url: URL,
      xhrFields: {
        withCredentials: true

      },
      async: false,
      crossDomain: true,
      success: successCallback,
      error: errorCallBack
    });

  }
